package com.chapslife.septatest.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragment;
import com.chapslife.septatest.dialogs.AdvisoryDialogFragment;
import com.chapslife.septatest.dialogs.DialogListener;


public class BaseFragment extends SherlockFragment implements DialogListener{

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
		AdvisoryDialogFragment AdvisoryDialog = AdvisoryDialogFragment.newInstance(title, message, negativeButtonText, positiveButtonText, requestId);
	
		AdvisoryDialog.show(fragmentTransaction, AdvisoryDialogFragment.FRAGMENT_DIALOG);
		AdvisoryDialog.setOnDialogClickListener(this);
		AdvisoryDialog.setCancelable(false);
	}

	@Override
	public void onDialogButtonClicked(int whichButton, int requestId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel(int requestId) {
		// TODO Auto-generated method stub
		
	}

}
