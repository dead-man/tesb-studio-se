// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.esb.webservice.managers;

import org.talend.core.language.ECodeLanguage;
import org.talend.core.language.LanguageManager;
import org.talend.core.model.metadata.builder.connection.ConnectionFactory;
import org.talend.core.model.metadata.builder.connection.DelimitedFileConnection;
import org.talend.designer.esb.webservice.WebServiceComponent;

/**
 * gcui class global comment. Detailled comment
 */
public class WebServiceManager {

    private final ECodeLanguage language = LanguageManager.getCurrentLanguage();

    private WebServiceComponent connector;

    private UIManager uiManager;

    private DelimitedFileConnection recordConnection;

    public WebServiceManager(WebServiceComponent connector, int selectedColumnIndex) {
        super();
        this.connector = connector;
        recordConnection = ConnectionFactory.eINSTANCE.createDelimitedFileConnection();
    }

    public DelimitedFileConnection getRecordConnection() {
        return this.recordConnection;
    }

    public UIManager getUIManager() {
        if (this.uiManager == null) {
            uiManager = new UIManager(this);
        }
        return this.uiManager;
    }

    public WebServiceComponent getWebServiceComponent() {
        return this.connector;
    }

    // public AbstractDataTableEditorView retrieveDataMapTableView(Control swtControl) {
    // return this.tableManager.getView(swtControl);
    // }

    public void savePropertiesToComponent() {
    }

    public ECodeLanguage getLanguage() {
        return this.language;
    }

}
