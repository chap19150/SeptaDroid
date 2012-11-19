package com.chapslife.septatest.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.chapslife.septatest.R;

public class AdvisoryDialogFragment extends SherlockDialogFragment implements OnClickListener{

	private static final String LOG_TAG = AdvisoryDialogFragment.class.getSimpleName();

	public static final String FRAGMENT_DIALOG = "FRAGMENT_DIALOG";
	
	private static final String DIALOG_TITLE = "DIALOG_TITLE";
	private static final String DIALOG_MESSAGE = "DIALOG_MESSAGE";
	private static final String DIALOG_POSITIVE_BUTTON_TEXT = "DIALOG_POSITIVE_BUTTON_TEXT";
	private static final String DIALOG_NEGATIVE_BUTTON_TEXT = "DIALOG_NEGATIVE_BUTTON_TEXT";	
	private static final String DIALOG_REQUEST_ID = "DIALOG_REQUEST_ID";
	
	protected DialogListener onDialogClickListener;
	
	private Button positiveButton, negativeButton;
	
	private TextView titleText, messageText;
	
	/**
	 * Creates a new instance of an AdvisoryDialogFragment with one or two buttons
	 * @param title
	 * @param message
	 * @param positiveText
	 * @param negativeText
	 * @param requestId
	 * @return AdvisoryDialogFragment
	 */
	public static AdvisoryDialogFragment newInstance(String title, String message, String positiveText, String negativeText, int requestId){
		AdvisoryDialogFragment dialogFragment = new AdvisoryDialogFragment();
		Bundle arguments = new Bundle();
		arguments.putString(DIALOG_TITLE, title);
		arguments.putString(DIALOG_MESSAGE, message);
		arguments.putString(DIALOG_POSITIVE_BUTTON_TEXT, positiveText);
		arguments.putString(DIALOG_NEGATIVE_BUTTON_TEXT, negativeText);
		arguments.putInt(DIALOG_REQUEST_ID, requestId);
		dialogFragment.setArguments(arguments);
		return dialogFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		setStyle(SherlockDialogFragment.STYLE_NO_TITLE | SherlockDialogFragment.STYLE_NORMAL, com.WazaBe.HoloEverywhere.R.style.DialogHoloLight);
		
	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.advisory_dialog_negative_button:
			dismiss();
			onDialogClickListener.onDialogButtonClicked(Dialog.BUTTON_NEGATIVE, getArguments().getInt(DIALOG_REQUEST_ID));
			
			break;
			
		case R.id.advisory_dialog_positive_button:
			dismiss();
			onDialogClickListener.onDialogButtonClicked(Dialog.BUTTON_POSITIVE, getArguments().getInt(DIALOG_REQUEST_ID));
			
			break;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.advisory_dialog_fragment, container, false);
		
		titleText = (TextView) view.findViewById(R.id.advisory_dialog_title);
		if(getArguments().getString(DIALOG_TITLE) != null){
			titleText.setText(getArguments().getString(DIALOG_TITLE));
		}
		
		messageText = (TextView) view.findViewById(R.id.advisory_dialog_message);
		if(getArguments().getString(DIALOG_MESSAGE) != null){
			messageText.setText(Html.fromHtml(getArguments().getString(DIALOG_MESSAGE)));
		}
		
		negativeButton = (Button) view.findViewById(R.id.advisory_dialog_negative_button);
		if(getArguments().getString(DIALOG_NEGATIVE_BUTTON_TEXT) != null){
			negativeButton.setText(getArguments().getString(DIALOG_NEGATIVE_BUTTON_TEXT));
		} else {
			negativeButton.setVisibility(View.GONE);
		}
		
		positiveButton = (Button) view.findViewById(R.id.advisory_dialog_positive_button);
		if(getArguments().getString(DIALOG_POSITIVE_BUTTON_TEXT) != null){
			positiveButton.setText(getArguments().getString(DIALOG_POSITIVE_BUTTON_TEXT));
		}else{
			positiveButton.setVisibility(View.GONE);
		}
		
		positiveButton.setOnClickListener(this);
		negativeButton.setOnClickListener(this);
		
		return view;
	}
	
	/**
	 * set a dialog listener to capture click events and 
	 * @param dialogListener
	 */
	public void setOnDialogClickListener(DialogListener dialogListener) {
		
		onDialogClickListener = dialogListener;
	}
}

