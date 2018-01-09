package com.example.premtsd.buyhatke_assignment.com.assignment.coupen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Switch;
import android.widget.Toast;

import com.example.premtsd.buyhatke_assignment.R;
import com.example.premtsd.buyhatke_assignment.com.assignment.service.AssistantService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CouponFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CouponFragment extends Fragment {

    private static final String ARG_PERMISSION_CHECK = "ARG_PERMISSION_CHECK";
    private static final String ARG_TYPE = "ARG_TYPE";
    private boolean permissionCheck;
    private WebView webView;
    private ProgressDialog progressBar;
    private String webUrl;
    private String type;
    private String url;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CouponFragment.
     */
    public static CouponFragment newInstance(String name, boolean permissionCheck) {
        CouponFragment fragment = new CouponFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, name);
        args.putBoolean(ARG_PERMISSION_CHECK, permissionCheck);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            permissionCheck = getArguments().getBoolean(ARG_PERMISSION_CHECK);
            type = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupen, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        switch (type) {
            case "Amazon":
                url="www.amazon.in";
                break;
            case "Myntra":
                url="www.amazon.in";
                break;
            default:
                break;
        }
        webView = (WebView) view.findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        progressBar = ProgressDialog.show(getActivity(), "WebView Load", "Loading...");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                startCouponIcon(url);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.show();
            }
        });
        webView.loadUrl(url);

    }

    private void startCouponIcon(String url) {
        webUrl = url;
        if (permissionCheck = true) {
            if (webUrl.equals("https://www.amazon.in/gp/aw/c/ref=navm_hdr_cart")) {
                getActivity().startService(new Intent(getActivity(), AssistantService.class));
            }
        }
    }

}
