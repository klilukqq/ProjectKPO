/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theboard;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author rusal
 */
class LimitedDocument extends PlainDocument {
 
	private int limit;
	
	public LimitedDocument() {
		limit = -1;
	}
	
	public LimitedDocument(int limit) {
		this.limit = limit;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
 
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		
		if (getLimit() < 0 || getLength() < getLimit()) {
			super.insertString(offs, str, a);
		}
	}
}
