package com.star.support.externs;

/**
 * 说明：
 * 1、依赖AUTOIT实现的GUI操作，必须编译为可知性程序；
 * 2、含上载、下载、关闭Dialog、点击Dialog、在Dialog上输入文本等操作。
 * 
 * @author 测试仔刘毅
 **/

import com.star.logging.frame.LoggingManager;

public class Win32GuiByAu3 {

	private static final String ASSIST = System.getProperty("user.dir") + "/assist/";
	private static final LoggingManager LOG = new LoggingManager(Win32GuiByAu3.class.getName());

	/**
	 * upload file in win32 gui using autoit compiled exe.
	 * you can use it like this: fileUpload("选择文件", "D:\\a.txt", 5);
	 * 
	 * @param	title dialog title
	 * @param	fileName file from where to upload
	 * @param	timeout timeout setting for wait alert appears in second unit
	 * @throws	RuntimeException
	 **/
	public void fileUpload(String title, String fileName, int timeout){
		String fileExec = ASSIST + "Upload.exe";
		String cmd = "\"" + fileExec + "\" \"" + title + "\" \"" + fileName + "\" \"" + timeout + "\"";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException("execute au3 exe files failed:" + e.getMessage());
		}finally{
			closeWindow(title, 1);
		}
	}
	/**
	 * upload file in win32 gui using autoit compiled exe.
	 * wait for upload dialog appers in 20 seconds.
	 * you can use it like this: fileUpload("选择文件", "D:\\a.txt");
	 * 
	 * @param	title dialog title
	 * @param	fileName file from where to upload
	 * @throws	RuntimeException
	 **/
	public void fileUpload(String title, String fileName){
		fileUpload(title, fileName, 20);
	}

	/**
	 * download file in win32 gui using autoit compiled exe.
	 * you can use it like this: fileDownload("文件下载", "另存为", "D:\\download.csv", 30);
	 * 
	 * @param	fstTitle the first download dialog title
	 * @param	sndTitle the second dialog after click "Save"
	 * @param	saveAs file name to be saved as
	 * @param	timeout download window wait timeout setting
	 * @throws	RuntimeException
	 **/
	public void fileDownload(String fstTitle, String sndTitle, String saveAs, int timeout){
		String fileExec = ASSIST + "Download.exe";
		String cmd = "\"" + fileExec + "\" \"" + fstTitle + "\" \"" 
					+ sndTitle + "\" \"" + saveAs + "\" \"" + timeout + "\"";
		System.out.println(cmd);
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException("execute au3 exe files failed:" + e.getMessage());
		}finally{
			closeWindow(fstTitle, 1);
			closeWindow(sndTitle, 1);
		}
	}

	/**
	 * download file in win32 gui using autoit compiled exe.
	 * wait for download dialog appers in 20 seconds.
	 * you can use it like this: fileDownload("文件下载", "另存为", "D:\\download.csv");
	 * 
	 * @param	fstTitle the first download dialog title
	 * @param	sndTitle the second dialog after click "Save"
	 * @param	saveAs file name to be saved as
	 * @throws	RuntimeException
	 **/
	public void fileDownload(String fstTitle, String sndTitle, String saveAs){
		fileDownload(fstTitle, sndTitle, saveAs, 20);
	}

	/**
	 * click alert dialog in win32 gui using autoit compiled exe.
	 * you can use it like this: clickAlert("提示信息", "确定(Y)", 10);
	 * 
	 * @param	dialogTitle dialog title
	 * @param	buttonName the button name/text on the dialog to click
	 * @param	timeout timeout setting for wait alert appears in second unit
	 * @throws	RuntimeException
	 **/
	public void clickAlert(String dialogTitle, String buttonName, int timeout){
		String fileExec = ASSIST + "ClickAlert.exe";
		String command = "\"" + fileExec + "\" \"" + dialogTitle + "\" \""
				+ buttonName + "\" \"" + timeout + "\"";
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException("execute au3 exe files failed:" + e.getMessage());
		}finally{
			closeWindow(dialogTitle, 1);
		}
	}

	/**
	 * click alert dialog in win32 gui using autoit compiled exe.
	 * wait for alert appers in 5 seconds.
	 * you can use it like this: clickAlert("提示信息", "确定(Y)");
	 * 
	 * @param	dialogTitle dialog title
	 * @param	buttonName the button name/text on the dialog to click
	 * @throws	RuntimeException
	 **/
	public void clickAlert(String dialogTitle, String buttonName){
		clickAlert(dialogTitle, buttonName, 5);
	}

	/**
	 * type text in alert dialog in win32 gui using autoit compiled exe.
	 * you can use it like this: typeAlert("窗口标题", "Edit1", "输入内容", 5);
	 * @param	title init dialog title
	 * @param	locator the locator on the dialog
	 * @param	text text to put into the edit
	 * @param	timeout timeout setting for wait alert appears in second unit
	 * @throws	RuntimeException
	 **/
	public void typeAlert(String title, String locator, String text, int timeout){
		String fileExec = ASSIST + "TypeAlert.exe";
		String cmd = "\"" + fileExec + "\" \"" + title + "\" \"" 
					+ locator + "\" \"" + text + "\" \"" + timeout + "\"";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException("execute au3 exe files failed:" + e.getMessage());
		}finally{
			closeWindow(title, 1);
		}
	}

	/**
	 * type text in alert dialog in win32 gui using autoit compiled exe.
	 * wait for alert appers in 5 seconds.
	 * you can use it like this: typeAlert("窗口标题", "Edit1", "输入内容");
	 * 
	 * @param	title init dialog title
	 * @param	locator the locator on the dialog
	 * @param	text text to put into the edit
	 * @throws	RuntimeException
	 **/
	public void typeAlert(String title, String locator, String text){
		typeAlert(title, locator, text, 5);
	}

	/**
	 * close window by name in win32 gui using autoit compiled exe.
	 * you can use it like this: closeWindow("窗口标题", 5);
	 * 
	 * @param	title dialog title
	 * @param	timeout timeout setting for wait alert appears in second unit
	 * @throws	RuntimeException
	 **/
	public void closeWindow(String title, int timeout){
		String fileExec = ASSIST + "CloseWindow.exe";
		String cmd = "\"" + fileExec + "\" \"" + title + "\" \"" + timeout + "\"";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException("execute au3 exe files failed:" + e.getMessage());
		}
	}

	/**
	 * close window by name in win32 gui using autoit compiled exe.
	 * wait for alert appers in 5 seconds.
	 * you can use it like this: closeWindow("窗口标题");
	 * 
	 * @param	title dialog title
	 * @throws	RuntimeException
	 **/
	public void closeWindow(String title){
		closeWindow(title, 5);
	}

	/**
	 * sendkeys to textarea in ie using autoit compiled exe.
	 * you can use it like this: typeTextArea("窗口标题", "id", "nameEdit", "输入内容");
	 * 
	 * @param	ieTitle ie window title
	 * @param	findBy name or id
	 * @param	nameOrId id value or name value of edit
	 * @param	text content to be input to the edit
	 * @throws	RuntimeException
	 **/
	public void typeTextArea(String ieTitle, String findBy, String nameOrId, String text){
		String fileExec = ASSIST + "EditTextArea.exe";
		String cmd = "\"" + fileExec + "\" \"" + ieTitle + "\" \"" + findBy + "\" \"" + nameOrId + "\" \"" + text + "\"";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException("execute au3 exe files failed:" + e.getMessage());
		}
	}

	/**
	 * get and write browser message to file using autoit compiled exe.
	 * you can use it like this: assertErrors(title, "errorMessage", "li", "D:\\a.txt", 10);
	 * 
	 * @param	title pop window title
	 * @param	upIdName the id or name of the text's father element
	 * @param	eleType the type of the element which to get text
	 * @param	fileName file path and name to store the error text
	 * @param	timeout time out setting to find the pop window
	 * @throws	RuntimeException
	 **/
	public void assertErrors(String title, String upIdName, String eleType, String fileName, long timeout){
		String execName = ASSIST + "WriteErrorMessage.exe";
		String cmd = "\"" + execName + "\" \"" + title + "\" \"" + upIdName + "\" \"" 
				+ eleType + "\" \""	+ fileName + "\" \"" + String.valueOf(timeout) + "\"";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException("execute au3 exe files failed:" + e.getMessage());
		}
	}
}