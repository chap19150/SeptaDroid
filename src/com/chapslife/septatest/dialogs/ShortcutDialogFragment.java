package com.chapslife.septatest.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.chapslife.septatest.R;

public class ShortcutDialogFragment extends SherlockDialogFragment implements OnClickListener{

	private static final String LOG_TAG = ShortcutDialogFragment.class.getSimpleName();

	public static final String FRAGMENT_DIALOG = "FRAGMENT_DIALOG";
	
	private static final String DIALOG_SHORTCUT_NAME = "DIALOG_SHORTCUT_NAME";
	private static final String DIALOG_REQUEST_ID = "DIALOG_REQUEST_ID";
	
	protected DialogListener onDialogClickListener;
	
	private Button positiveButton, negativeButton;
	private EditText shortcutName;
	
	/**
	 * Creates a new instance of an AdvisoryDialogFragment with one or two buttons
	 * @param requestId
	 * @return AdvisoryDialogFragment
	 */
	public static ShortcutDialogFragment newInstance(String name, int requestId){
		ShortcutDialogFragment dialogFragment = new ShortcutDialogFragment();
		Bundle arguments = new Bundle();
		arguments.putString(DIALOG_SHORTCUT_NAME, name);
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.dialog_fragment_shortcut, container, false);
		
		shortcutName = (EditText) view.findViewById(R.id.shortcut_name);
		if(getArguments().getString(DIALOG_SHORTCUT_NAME) != null){
			shortcutName.setText(getArguments().getString(DIALOG_SHORTCUT_NAME));
		}
		negativeButton = (Button) view.findViewById(R.id.shortcut_dialog_negative_button);
		positiveButton = (Button) view.findViewById(R.id.shortcut_dialog_positive_button);
		
		positiveButton.setOnClickListener(this);
		negativeButton.setOnClickListener(this);
		
		return view;
	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.shortcut_dialog_negative_button:
			dismiss();
			onDialogClickListener.onShortcutDialogButtonClicked(Dialog.BUTTON_NEGATIVE, null, getArguments().getInt(DIALOG_REQUEST_ID));
			break;
			
		case R.id.shortcut_dialog_positive_button:
			dismiss();
			onDialogClickListener.onShortcutDialogButtonClicked(Dialog.BUTTON_POSITIVE, shortcutName.getText().toString(), getArguments().getInt(DIALOG_REQUEST_ID));
			break;
		}
	}
	
	/**
	 * set a dialog listener to capture click events and 
	 * @param dialogListener
	 */
	public void setOnDialogClickListener(DialogListener dialogListener) {
		
		onDialogClickListener = dialogListener;
	}
}
