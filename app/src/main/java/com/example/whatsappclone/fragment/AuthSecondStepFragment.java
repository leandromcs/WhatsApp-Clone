package com.example.whatsappclone.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.activity.HomeActivity;
import com.example.whatsappclone.utils.MaskEditUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class AuthSecondStepFragment extends Fragment {

    private static final String VERIFICATION_ID = "verificationID";
    private static final String CREDENTIAL = "credential";
    private static final String TELEFONE = "telefone";

    private EditText code;
    private TextView tituloConfirmacao;
    private TextView descricaoConfirmacao;
    private String telefone;
    private ProgressDialog progressDialog;

    private String verificationID;
    private PhoneAuthCredential credential;

    private FirebaseAuth auth;

    public AuthSecondStepFragment() {
    }

    public static AuthSecondStepFragment newInstanceAuthComplete(String telefoneCompleto, PhoneAuthCredential credential) {
        AuthSecondStepFragment fragment = new AuthSecondStepFragment();
        Bundle args = new Bundle();
        args.putParcelable(CREDENTIAL, credential);
        args.putString(TELEFONE, telefoneCompleto);
        fragment.setArguments(args);
        return fragment;
    }

    public static AuthSecondStepFragment newInstanceCodeSent(String telefoneCompleto, String verificationID) {
        AuthSecondStepFragment fragment = new AuthSecondStepFragment();
        Bundle args = new Bundle();
        args.putString(VERIFICATION_ID, verificationID);
        args.putString(TELEFONE, telefoneCompleto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().getParcelable(CREDENTIAL) != null) {
            this.credential = getArguments().getParcelable(CREDENTIAL);
        } else {
            verificationID = getArguments().getString(VERIFICATION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.auth_second_step_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.auth = FirebaseAuth.getInstance();

        this.code = getView().findViewById(R.id.editText);
        this.tituloConfirmacao = getView().findViewById(R.id.tv_titulo_confirmacao);
        this.descricaoConfirmacao = getView().findViewById(R.id.tv_descricao_confirmacao);
        this.telefone = getArguments().getString(TELEFONE);
        this.progressDialog = new ProgressDialog(getContext());
        this.progressDialog.setMessage("Carregando");

        this.tituloConfirmacao.setText("Confirmar " + telefone);
        this.descricaoConfirmacao.setText(Html.fromHtml("Enviamos um código por SMS para \n" + "<b>" + telefone + "</b>" + ". Número errado?"));

        if (getArguments().getParcelable(CREDENTIAL) != null) {
            signInWithPhoneAuthCredential(credential);
        }

        this.code.addTextChangedListener(MaskEditUtil.mask(code, MaskEditUtil.FORMAT_AUTH_CODE));
        this.code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start == 6) {
                    progressDialog.show();
                    autenticarComCodigo();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void autenticarComCodigo() {
        signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(this.verificationID, MaskEditUtil.unmask(this.code.getText().toString())));
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            progressDialog.dismiss();
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
