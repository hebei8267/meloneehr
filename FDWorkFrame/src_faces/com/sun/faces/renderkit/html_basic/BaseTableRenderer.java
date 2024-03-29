/*
 * $Id: BaseTableRenderer.java,v 1.1.2.2 2007/08/30 21:29:14 rlubke Exp $
 */

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.faces.renderkit.html_basic;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.component.UIComponent;
import javax.faces.component.UIColumn;
import javax.faces.component.UIData;

import com.sun.faces.renderkit.RenderKitUtils;
import com.sun.faces.util.Util;

/**
 * Base class for concrete Grid and Table renderers.
 */
public abstract class BaseTableRenderer extends HtmlBasicRenderer {

	// ------------------------------------------------------- Protected Methods

	/**
	 * Called to render the opening/closing <code>thead</code> elements and
	 * any content nested between.
	 * 
	 * @param context the <code>FacesContext</code> for the current request
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected abstract void renderHeader(FacesContext context, UIComponent table, ResponseWriter writer) throws IOException;

	/**
	 * Called to render the opening/closing <code>tfoot</code> elements and
	 * any content nested between.
	 * 
	 * @param context the <code>FacesContext</code> for the current request
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected abstract void renderFooter(FacesContext context, UIComponent table, ResponseWriter writer) throws IOException;

	/**
	 * Call to render the content that should be included between opening and
	 * closing <code>tr</code> elements.
	 * 
	 * @param context the <code>FacesContext</code> for the current request
	 * @param table the table that's being rendered
	 * @param row the current row (if any - an implmenetation may not need this)
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected abstract void renderRow(FacesContext context, UIComponent table, UIComponent row, ResponseWriter writer)
			throws IOException;

	/**
	 * Renders the start of a table and applies the value of
	 * <code>styleClass</code> if available and renders any pass through
	 * attributes that may be specified.
	 * 
	 * @param context the <code>FacesContext</code> for the current request
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @param passThroughAttributes pass-through attributes that the component
	 *            supports
	 * @throws IOException if content cannot be written
	 */
	protected void renderTableStart(FacesContext context, UIComponent table, ResponseWriter writer, String[] passThroughAttributes)
			throws IOException {

		writer.startElement("table", table);
		writeIdAttributeIfNecessary(context, writer, table);
		String styleClass = (String) table.getAttributes().get("styleClass");
		if (styleClass != null) {
			writer.writeAttribute("class", styleClass, "styleClass");
		}
		RenderKitUtils.renderPassThruAttributes(writer, table, passThroughAttributes);
		writer.writeText("\n", table, null);

	}

	/**
	 * Renders the closing <code>table</code> element.
	 * 
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected void renderTableEnd(UIComponent table, ResponseWriter writer) throws IOException {

		writer.endElement("table");
		writer.writeText("\n", table, null);

	}

	/**
	 * Renders the caption of the table applying the values of
	 * <code>captionClass</code> as the class and <code>captionStyle</code>
	 * as the style if either are present.
	 * 
	 * @param context the <code>FacesContext</code> for the current request
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected void renderCaption(FacesContext context, UIComponent table, ResponseWriter writer) throws IOException {

		UIComponent caption = getFacet(table, "caption");
		if (caption != null) {
			String captionClass = (String) table.getAttributes().get("captionClass");
			String captionStyle = (String) table.getAttributes().get("captionStyle");
			writer.startElement("caption", table);
			if (captionClass != null) {
				writer.writeAttribute("class", captionClass, "captionClass");
			}
			if (captionStyle != null) {
				writer.writeAttribute("style", captionStyle, "captionStyle");
			}
			encodeRecursive(context, caption);
			writer.endElement("caption");
		}

	}

	/**
	 * Renders the starting <code>tbody</code> element.
	 * 
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected void renderTableBodyStart(UIComponent table, ResponseWriter writer) throws IOException {

		writer.startElement("tbody", table);
		writer.writeText("\n", table, null);

	}

	/**
	 * Renders the closing <code>tbody</code> element.
	 * 
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected void renderTableBodyEnd(UIComponent table, ResponseWriter writer) throws IOException {

		writer.endElement("tbody");
		writer.writeText("\n", table, null);

	}

	/**
	 * Renders the starting <code>tr</code> element applying any values from
	 * the <code>rowClasses</code> attribute.
	 * 
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected void renderRowStart(UIComponent table, ResponseWriter writer) throws IOException {

		TableMetaInfo info = getMetaInfo(table);
		writer.startElement("tr", table);
		if (info.rowClasses.length > 0) {
			writer.writeAttribute("class", info.getCurrentRowClass(), "rowClasses");
		}
		writer.writeText("\n", table, null);

	}

	/**
	 * Renders the closing <code>rt</code> element.
	 * 
	 * @param table the table that's being rendered
	 * @param writer the current writer
	 * @throws IOException if content cannot be written
	 */
	protected void renderRowEnd(UIComponent table, ResponseWriter writer) throws IOException {

		writer.endElement("tr");
		writer.writeText("\n", table, null);

	}

	/**
	 * Returns a <code>TableMetaInfo</code> object containing details such as
	 * row and column classes, columns, and a mechanism for scrolling through
	 * the row/column classes.
	 * 
	 * @param table the table that's being rendered
	 * @return the <code>TableMetaInfo</code> for provided table
	 */
	protected TableRenderer.TableMetaInfo getMetaInfo(UIComponent table) {

		TableRenderer.TableMetaInfo info = (TableRenderer.TableMetaInfo) table.getAttributes().get(
				TableRenderer.TableMetaInfo.KEY);
		if (info == null) {
			info = new TableRenderer.TableMetaInfo(table);
			table.getAttributes().put(TableRenderer.TableMetaInfo.KEY, info);
		}
		return info;

	}

	/**
	 * Removes the cached TableMetaInfo from the specified component.
	 * 
	 * @param table the table from which the TableMetaInfo will be removed
	 */
	protected void clearMetaInfo(UIComponent table) {

		table.getAttributes().remove(TableMetaInfo.KEY);

	}

	// ----------------------------------------------------------- Inner Classes

	protected static class TableMetaInfo {

		private static final UIColumn PLACE_HOLDER_COLUMN = new UIColumn();
		private static final String[] EMPTY_STRING_ARRAY = new String[0];
		public static final String KEY = TableMetaInfo.class.getName();

		public final String[] rowClasses;
		public final String[] columnClasses;
		public final List<UIColumn> columns;
		public final boolean hasHeaderFacets;
		public final boolean hasFooterFacets;
		public int columnStyleCounter;
		public int rowStyleCounter;

		// -------------------------------------------------------- Constructors

		public TableMetaInfo(UIComponent table) {
			rowClasses = getRowClasses(table);
			columnClasses = getColumnClasses(table);
			columns = getColumns(table);
			hasHeaderFacets = hasFacet("header", columns);
			hasFooterFacets = hasFacet("footer", columns);
		}

		// ------------------------------------------------------ Public Methods

		/**
		 * Obtain the column class based on the current counter. Calling this
		 * method automatically moves the pointer to the next style. If the
		 * counter is larger than the number of total classes, the counter will
		 * be reset.
		 * 
		 * @return the current style
		 */
		public String getCurrentColumnClass() {
			String style = columnClasses[columnStyleCounter++];
			if (columnStyleCounter >= columnClasses.length) {
				columnStyleCounter = 0;
			}
			return style;
		}

		/**
		 * Obtain the row class based on the current counter. Calling this
		 * method automatically moves the pointer to the next style. If the
		 * counter is larger than the number of total classes, the counter will
		 * be reset.
		 * 
		 * @return the current style
		 */
		public String getCurrentRowClass() {
			String style = rowClasses[rowStyleCounter++];
			if (rowStyleCounter >= rowClasses.length) {
				rowStyleCounter = 0;
			}
			return style;
		}

		// ----------------------------------------------------- Private Methods

		/**
		 * <p>
		 * Return an array of stylesheet classes to be applied to each column in
		 * the table in the order specified. Every column may or may not have a
		 * stylesheet.
		 * </p>
		 * 
		 * @param table {@link javax.faces.component.UIComponent} component
		 *            being rendered
		 * 
		 * @return an array of column classes
		 */
		private static String[] getColumnClasses(UIComponent table) {

			String values = (String) table.getAttributes().get("columnClasses");
			if (values == null) {
				return EMPTY_STRING_ARRAY;
			}
			return Util.split(values.trim(), ",");

		}

		/**
		 * <p>
		 * Return an Iterator over the <code>UIColumn</code> children of the
		 * specified <code>UIData</code> that have a <code>rendered</code>
		 * property of <code>true</code>.
		 * </p>
		 * 
		 * @param table the table from which to extract children
		 * 
		 * @return the List of all UIColumn children
		 */
		private static List<UIColumn> getColumns(UIComponent table) {

			if (table instanceof UIData) {
				int childCount = table.getChildCount();
				if (childCount > 0) {
					List<UIColumn> results = new ArrayList<UIColumn>(childCount);
					for (UIComponent kid : table.getChildren()) {
						if ((kid instanceof UIColumn) && kid.isRendered()) {
							results.add((UIColumn) kid);
						}
					}
					return results;
				} else {
					return Collections.emptyList();
				}
			} else {
				int count;
				Object value = table.getAttributes().get("columns");
				if ((value != null) && (value instanceof Integer)) {
					count = ((Integer) value);
				} else {
					count = 2;
				}
				if (count < 1) {
					count = 1;
				}
				List<UIColumn> result = new ArrayList<UIColumn>(count);
				for (int i = 0; i < count; i++) {
					result.add(PLACE_HOLDER_COLUMN);
				}
				return result;
			}

		}

		/**
		 * <p>
		 * Return the number of child <code>UIColumn</code> components nested
		 * in the specified <code>UIData</code> that have a facet with the
		 * specified name.
		 * </p>
		 * 
		 * @param name Name of the facet being analyzed
		 * @param columns the columns to search
		 * 
		 * @return the number of columns associated with the specified Facet
		 *         name
		 */
		private static boolean hasFacet(String name, List<UIColumn> columns) {

			if (!columns.isEmpty()) {
				for (UIColumn column : columns) {
					if (column.getFacetCount() > 0) {
						if (column.getFacets().containsKey(name)) {
							return true;
						}
					}
				}
			}
			return false;

		}

		/**
		 * <p>
		 * Return an array of stylesheet classes to be applied to each row in
		 * the table, in the order specified. Every row may or may not have a
		 * stylesheet.
		 * </p>
		 * 
		 * @param table {@link javax.faces.component.UIComponent} component
		 *            being rendered
		 * 
		 * @return an array of row classes
		 */
		private static String[] getRowClasses(UIComponent table) {

			String values = (String) table.getAttributes().get("rowClasses");
			if (values == null) {
				return (EMPTY_STRING_ARRAY);
			}
			return Util.split(values.trim(), ",");

		}

	} // END UIDataMetaInfo
}
