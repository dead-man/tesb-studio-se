// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.camel.designer.ui.wizards;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.camel.designer.i18n.Messages;
import org.talend.camel.designer.util.CamelRepositoryNodeType;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.ui.wizards.PropertiesWizardPage;

/**
 * Page for new project details. <br/>
 * 
 * $Id: NewProcessWizardPage.java 52559 2010-12-13 04:14:06Z nrousseau $
 * 
 */
public class CamelNewBeanWizardPage extends PropertiesWizardPage {

    private static final String DESC = Messages.getString("NewBeanWizard.description"); //$NON-NLS-1$

    /**
     * Constructs a new NewProjectWizardPage.
     * 
     */
    public CamelNewBeanWizardPage(Property property, IPath destinationPath) {
        super("WizardPage", property, destinationPath); //$NON-NLS-1$

        setTitle(Messages.getString("NewBeanWizardPage.title")); //$NON-NLS-1$
        setDescription(DESC);
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        super.createControl(container);

        setControl(container);
        updateContent();
        addListeners();
        setPageComplete(false);
    }

    public ERepositoryObjectType getRepositoryObjectType() {
        return CamelRepositoryNodeType.repositoryBeansType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.wizards.PropertiesWizardPage#evaluateTextField()
     */
    protected void evaluateTextField() {
        super.evaluateTextField();
        if (nameStatus.getSeverity() == IStatus.OK) {
            evaluateNameInRoutine();
        }
    }

}
