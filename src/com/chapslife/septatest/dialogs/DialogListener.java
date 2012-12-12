package com.chapslife.septatest.dialogs;

/**
 * listener interface for a dialog
 * @author kchapman@applicoinc.com
 *
 */
public interface DialogListener {
	/**
	 * alert when a dialog button is clicked
	 * @param whichButton - which button is clicked
	 * @param requestId - the requestId passed in
	 */
	void onDialogButtonClicked(int whichButton, int requestId);
	/**
	 * alert when the dialog is cancelled
	 * @param requestId - the requestId passed in
	 */
	void onCancel(int requestId);
	
	void onShortcutDialogButtonClicked(int whichButton, String shortcutTitle, int requestId);
}
