package com.example.whatsappclone.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.whatsappclone.R;
import com.example.whatsappclone.utils.MaskEditUtil;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class AuthFirstStepFragment extends Fragment {

    private EditText codigo;
    private EditText telefone;
    private Button btn_send;
    private String telefoneCompleto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.auth_first_step_fragment, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.codigo = getView().findViewById(R.id.et_codigo);
        this.telefone = getView().findViewById(R.id.et_tel);
        this.btn_send = getView().findViewById(R.id.button);
        this.telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
        this.btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });
    }

    private void sendSMS() {
        this.telefoneCompleto = "+" + this.codigo.getText().toString() + " " + this.telefone.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber( "+" + this.codigo.getText().toString() + MaskEditUtil.unmask(this.telefone.getText().toString()), 60, TimeUnit.SECONDS, getActivity(),
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                        Log.d(TAG, "onVerificationCompleted:" + credential);

                        AuthSecondStepFragment authSecondStepFragment = AuthSecondStepFragment.newInstanceAuthComplete(telefoneCompleto ,credential);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, authSecondStepFragment)
                                .commit();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.w(TAG, "onVerificationFailed", e);

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                            // ...
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // The SMS quota for the project has been exceeded
                            // ...
                        }
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        Log.d(TAG, "onCodeSent:" + verificationId);

                        AuthSecondStepFragment authSecondStepFragment = AuthSecondStepFragment.newInstanceCodeSent(telefoneCompleto ,verificationId);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, authSecondStepFragment)
                                .commit();
                    }
                });
    }
}
