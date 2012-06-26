import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.change_vision.jude.api.inf.exception.LicenseNotFoundException;
import com.change_vision.jude.api.inf.exception.NonCompatibleException;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.exception.ProjectLockedException;
import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IConstraint;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IGeneralization;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IOperation;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.model.IParameter;
import com.change_vision.jude.api.inf.model.IRealization;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;

/**
 * Class to build class definition from selected project.
 */
public class ClassDefinitionBuilder {

    private static final String EMPTY_COLUMN = "";

    private String inputFile;
    
    /**
     * @param inputFile
     *            File to input
     */
    public ClassDefinitionBuilder(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Get class information.
     * 
     * @return Class information (String List stored in the List)
     * @throws LicenseNotFoundException
     *             License cannot be found 
     * @throws ProjectNotFoundException
     *             Project cannot be found
     * @throws NonCompatibleException
     *             Old Model Version (The version of API is older than the version of JUDE that the project has been last edited with)
     * @throws ClassNotFoundException
     *             Cannot read some models
     * @throws IOException
     *             Input/Output error
     * @throws ProjectLockedException
     *             Project file has been locked
     */
    public List getContents() throws LicenseNotFoundException, ProjectNotFoundException,
            NonCompatibleException, IOException, ClassNotFoundException, ProjectLockedException, Throwable {

        ProjectAccessor prjAccessor = ProjectAccessorFactory.getProjectAccessor();
        prjAccessor.open(inputFile);
        IModel iModel = prjAccessor.getProject();

        List contents = new ArrayList();
        contents.add(getHeader());

        List iPackages = getAllPackages(iModel);

        for (Iterator iter = iPackages.iterator(); iter.hasNext();) {
            IPackage iPackage = (IPackage)iter.next();
            contents.addAll(getClassInfos(iPackage));
        }

        prjAccessor.close();

        return contents;
    }

    /**
     * Get header information.
     * @return Header information (String List)
     */
    private List getHeader() {
        List header = new ArrayList();
        header.add("Class");
        header.add("Attribute/Operation");
        header.add("Definition");
        header.add("Generalization");
        header.add("Realization");
        return header;
    }

    /**
     * Get all packages in project.
     * @param project
     *            Project
     * @return Package list
     */
    private List getAllPackages(IModel project) {
        List packages = new ArrayList();
        packages.add(project);
        return getPackages(project, packages);
    }

    /**
     * How to get packages under Package recursively
     * @param iPackage
     *            Selected package
     * @param iPackages
     *            List of all stored packages
     * @return List of all stored packages
     */
    private List getPackages(IPackage iPackage, List iPackages) {
        INamedElement[] iNamedElements = iPackage.getOwnedElements();
        for (int i = 0; i < iNamedElements.length; i++) {
            INamedElement iNamedElement = iNamedElements[i];
            if (iNamedElement instanceof IPackage) {
                iPackages.add(iNamedElement);
                getPackages((IPackage)iNamedElement, iPackages);
            }
        }
        return iPackages;
    }

    /**
     * Get class information in selected package.
     * @param iPackage
     *            Selected package
     * @return Class information (String List stored in the list)
     */
    private List getClassInfos(IPackage iPackage) {
        List classInfos = new ArrayList();
        List classes = getIClasses(iPackage);
        for (Iterator iter = classes.iterator(); iter.hasNext();) {
            IClass iClass = (IClass)iter.next();
            classInfos.addAll(getClassInfo(iClass));
        }
        return classInfos;
    }

    /**
     * Get classes in selected package.
     * @param iPackage
     *            Selected package
     * @return List of all stored classes
     */
    private List getIClasses(IPackage iPackage) {
        List iClasses = new ArrayList();
        INamedElement[] iNamedElements = iPackage.getOwnedElements();
        for (int i = 0; i < iNamedElements.length; i++) {
            INamedElement iNamedElement = iNamedElements[i];
            if (iNamedElement instanceof IClass) {
                iClasses.add(iNamedElement);
            }
        }
        return iClasses;
    }

    /**
     * Get information of selected class.
     * @param iClass
     *            Selected class
     * @return Class information (Strings list stored in the list)
     */
    private List getClassInfo(IClass iClass) {
        List lines = new ArrayList();
        lines.add(getClassNameLine(iClass));
        lines.addAll(getAttributeLines(iClass));
        lines.addAll(getOperationLines(iClass));
        return lines;
    }

    /**
     * Get class name line.
     * @param iClass
     *            Class
     * @return Information of Class name lines (String list)
     */
    private List getClassNameLine(IClass iClass) {
        List line = new ArrayList();
        line.add(getFullName(iClass));
        line.add(EMPTY_COLUMN);
        line.add(iClass.getDefinition());
        line.add(getSuperClass(iClass));
        line.add(getImplementation(iClass));
        return line;
    }

    /**
     * Get Realization interface names.
     * @param iClass
     *            Class
     * @return Interface Name
     */
    private String getImplementation(IClass iClass) {
        StringBuffer buffer = new StringBuffer();
        IRealization[] realizations = iClass.getClientRealizations();
        for (int i = 0; i < realizations.length; i++) {
            IClass realization = realizations[i].getSupplier();
            buffer.append(getFullName(realization));
            if (i != realizations.length - 1) {
                buffer.append(", ");
            }
        }
        return buffer.toString();
    }

    /**
     * Get generalization class names.
     * @param iClass
     *            Class
     * @return Class name
     */
    private String getSuperClass(IClass iClass) {
        StringBuffer buffer = new StringBuffer();
        IGeneralization[] generalizations = iClass.getGeneralizations();
        for (int i = 0; i < generalizations.length; i++) {
            IClass superClass = generalizations[i].getSuperType();
            buffer.append(getFullName(superClass));
            if (i != generalizations.length - 1) {
                buffer.append(", ");
            }
        }
        return buffer.toString();
    }

    /**
     * Get Class name as Full Path.
     * @param iClass
     *            Class
     * @return Class Name (Full Path)
     */
    private String getFullName(IClass iClass) {
        StringBuffer sb = new StringBuffer();
        IElement owner = iClass.getOwner();
        while (owner != null && owner instanceof INamedElement && owner.getOwner() != null) {
            sb.insert(0, ((INamedElement) owner).getName() + "::");
            owner = owner.getOwner();
        }
        sb.append(iClass.getName());
        return sb.toString();
    }

    /**
     * Get all Attribute information.
     * @param iClass
     *            Class
     * @return All information of all attributes (String List stored in the list)
     */
    private List getAttributeLines(IClass iClass) {
        List lines = new ArrayList();
        IAttribute[] iAttributes = iClass.getAttributes();
        for (int i = 0; i < iAttributes.length; i++) {
            IAttribute iAttribute = iAttributes[i];
            lines.add(getAttributeLine(iAttribute));
        }
        return lines;
    }

    /**
     * Get Attribute information.
     * @param iAttribute
     *            Attribute
     * @return Attribute information (String List)
     */
    private List getAttributeLine(IAttribute iAttribute) {
        List line = new ArrayList();
        line.add(EMPTY_COLUMN);
        line.add(getAttributeSignature(iAttribute));
        line.add(iAttribute.getDefinition());
        line.add(EMPTY_COLUMN);
        line.add(EMPTY_COLUMN);
        return line;
    }

    /**
     * Get attribute signature.
     * @param iAttribute
     *            Attribute
     * @return Attribute signature
     */
    private String getAttributeSignature(IAttribute iAttribute) {
        String visibility = getVisibility(iAttribute);

        String name = iAttribute.getName();

        String type = iAttribute.getTypeExpression();

        String initValue = iAttribute.getInitialValue();
        if (initValue.length() > 0) {
            initValue = " = " + initValue;
        }

        IConstraint[] constraints = iAttribute.getConstraints();
        String constraint = "";
        for (int j = 0; j < constraints.length; j++) {
            constraint = constraint + "{" + constraints[j].getName() + "}";
        }

        return visibility + " " + name + " : " + type + initValue + constraint;
    }

    /**
     * Get all operation information.
     * @param iClass
     *            Class
     * @return All operation information (String list stored in the list)
     */
    private List getOperationLines(IClass iClass) {
        List lines = new ArrayList();
        IOperation[] iOperations = iClass.getOperations();
        for (int i = 0; i < iOperations.length; i++) {
            IOperation iOperation = iOperations[i];
            lines.add(getOperationLine(iOperation));
        }
        return lines;
    }

    /**
     * Get operation information.
     * @param iOperation
     *             Operation
     * @return Operation information (String List)
     */
    private List getOperationLine(IOperation iOperation) {
        List line = new ArrayList();
        line.add(EMPTY_COLUMN);
        line.add(getOperationSignature(iOperation));
        line.add(iOperation.getDefinition());
        line.add(EMPTY_COLUMN);
        line.add(EMPTY_COLUMN);
        return line;
    }

    /**
     * Get operation signature.
     * @param iOperation
     *           Operation
     * @return Operation signature
     */
    private String getOperationSignature(IOperation iOperation) {
        String param = "";

        IParameter[] parameters = iOperation.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            String paramName = parameters[i].getName();
            String paramType = parameters[i].getTypeExpression();
            param = param + paramName + " : " + paramType;
            if (i != parameters.length - 1) {
                param += ", ";
            }
        }
        param = "(" + param + ")";

        IConstraint[] constraints = iOperation.getConstraints();
        String constraint = "";
        for (int i = 0; i < constraints.length; i++) {
            constraint = constraint + "{" + constraints[i].getName() + "}";
        }

        String visibility = getVisibility(iOperation);
        String name = iOperation.getName();
        String returnType = iOperation.getReturnTypeExpression();
        if (returnType.length() > 0) {
            return visibility + " " + name + param + " : " + returnType;
        } else {
            return visibility + " " + name + param;
        }
    }

    /**
     * Get visibility as string.
     * @param iNamedElement
     *            Named elements
     * @return Visibility
     */
    private String getVisibility(INamedElement iNamedElement) {
        if (iNamedElement.isPackageVisibility()) {
            return "package";
        } else if (iNamedElement.isProtectedVisibility()) {
            return "protected";
        } else if (iNamedElement.isPrivateVisibility()) {
            return "private";
        } else if (iNamedElement.isPublicVisibility()) {
            return "public";
        }
        return "";
    }
}
