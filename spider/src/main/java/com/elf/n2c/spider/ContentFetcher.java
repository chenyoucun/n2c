package com.elf.n2c.spider;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * User: laichendong
 * Date: 12-8-30
 * Time: ����3:47
 */
public class ContentFetcher {

	public byte[] fetch(String url) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			return getMethod.getResponseBody();
			//��������
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			//���������쳣
			e.printStackTrace();
		} finally {
			//�ͷ�����
			getMethod.releaseConnection();
		}
		return null;
	}

	public static void main(String[] args) {
		new ContentFetcher().fetch("");
	}
}
