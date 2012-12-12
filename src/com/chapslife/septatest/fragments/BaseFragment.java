package com.chapslife.septatest.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragment;
import com.chapslife.septatest.dialogs.AdvisoryDialogFragment;
import com.chapslife.septatest.dialogs.DialogListener;
import com.chapslife.septatest.dialogs.ShortcutDialogFragment;


public class BaseFragment extends SherlockFragment implements DialogListener{

	private AdvisoryDialogFragment advisoryDialog;
	private ShortcutDialogFragment shortcutDialog;
	
	/**
	 * Show an Advisory dialog
	 * @param title
	 * @param message
	 * @param negativeButtonText
	 * @param positiveButtonText
	 * @param requestId
	 */
	public void showAdvisoryDialog(String title, String message, String positiveButtonText, String negativeButtonText, int requestId){
		
		FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
		Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(AdvisoryDialogFragment.FRAGMENT_DIALOG);
		
		if (prev != null){
			fragmentTransaction.remove(prev);
		}
		
		fragmentTransaction.addToBackStack(null);
		
		// create and show Advisory dialog
		advisoryDialog = AdvisoryDialogFragment.newInstance(title, message, negativeButtonText, positiveButtonText, requestId);
	
		advisoryDialog.show(fragmentTransaction, AdvisoryDialogFragment.FRAGMENT_DIALOG);
		advisoryDialog.setOnDialogClickListener(this);
		advisoryDialog.setCancelable(false);
	}
	
	public void dismissDialog(){
		if(advisoryDialog != null){
			advisoryDialog.dismiss();
		}
		if(shortcutDialog != null){
			shortcutDialog.dismiss();
		}
	}

	public void showShortcutDialog(String title, int requestId){
		FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
		Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(ShortcutDialogFragment.FRAGMENT_DIALOG);
		
		if (prev != null){
			fragmentTransaction.remove(prev);
		}
		
		fragmentTransaction.addToBackStack(null);
		
		// create and show Shortcut dialog
		shortcutDialog = ShortcutDialogFragment.newInstance(title,requestId);
	
		shortcutDialog.show(fragmentTransaction, ShortcutDialogFragment.FRAGMENT_DIALOG);
		shortcutDialog.setOnDialogClickListener(this);
		shortcutDialog.setCancelable(false);
	}
	@Override
	public void onDialogButtonClicked(int whichButton, int requestId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel(int requestId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShortcutDialogButtonClicked(int whichButton, String shortcutTitle, int requestId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause(){
		super.onPause();
	}
}
