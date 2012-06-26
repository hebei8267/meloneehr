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
 * �w�肳�ꂽ�v���W�F�N�g����A�N���X���\�z����N���X�B
 * Class to build class definition from selected project.
 */
public class ClassDefinitionBuilder {

    private static final String EMPTY_COLUMN = "";

    private String inputFile;
    
    /**
     * @param inputFile
     *            ���͂���v���W�F�N�g
     *            File to input
     */
    public ClassDefinitionBuilder(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * �N���X�����擾����B
     * Get class information.
     * 
     * @return �N���X���iList�Ɋi�[���ꂽString��List�j
     *         Class information (String List stored in the List)
     * @throws LicenseNotFoundException
     *             ���C�Z���X��������܂���
     *             License cannot be found 
     * @throws ProjectNotFoundException
     *             �v���W�F�N�g��������܂���
     *             Project cannot be found
     * @throws NonCompatibleException
     *             ���f���o�[�W�������Â��i�v���W�F�N�g���Ō�ɕҏW����Astah����API�̃o�[�W�������Â��j�ł�
     *             Old Model Version (The version of API is older than the version of Astah that the project has been last edited with)
     * @throws ClassNotFoundException
     *             �ǂݍ��߂Ȃ����f��������܂�
     *             Cannot read some models
     * @throws IOException
     *             ���o�̓G���[�ł�
     *             Input/Output error
     * @throws ProjectLockedException
     *             �v���W�F�N�g�t�@�C�������b�N����Ă��܂�
     *             Project file has been locked
     */
    public List getContents() throws LicenseNotFoundException, ProjectNotFoundException,
            NonCompatibleException, IOException, ClassNotFoundException, ProjectLockedException, Throwable {

        // �v���W�F�N�g���J���āA�N�_�ƂȂ郂�f�����擾����
        ProjectAccessor prjAccessor = ProjectAccessorFactory.getProjectAccessor();
        prjAccessor.open(inputFile);
        IModel iModel = prjAccessor.getProject();

        List contents = new ArrayList();
        contents.add(getHeader());

        // �v���W�F�N�g�Ɋ܂܂��S�Ẵp�b�P�[�W���擾����
        List iPackages = getAllPackages(iModel);

        // �p�b�P�[�W���ɏ����\�z����
        for (Iterator iter = iPackages.iterator(); iter.hasNext();) {
            IPackage iPackage = (IPackage)iter.next();
            contents.addAll(getClassInfos(iPackage));
        }

        // �v���W�F�N�g�����
        prjAccessor.close();

        return contents;
    }

    /**
     * �w�b�_�����擾����B
     * Get header information.
     * @return �w�b�_���iString��List�j
     *         Header information (String List)
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
     * �v���W�F�N�g�z���̑S�Ẵp�b�P�[�W���擾����B
     * Get all packages in project.
     * @param project
     *            �v���W�F�N�g
     *            Project
     * @return �p�b�P�[�W���X�g
     *         Package list
     */
    private List getAllPackages(IModel project) {
        List packages = new ArrayList();
        packages.add(project);
        return getPackages(project, packages);
    }

    /**
     * �w��p�b�P�[�W�z���̃p�b�P�[�W���A�ċA�I�ɑS�Ď擾����B
     * How to get packages under Package recursively
     * @param iPackage
     *            �w��p�b�P�[�W
     *            Selected package
     * @param iPackages
     *            �p�b�P�[�W�ꗗ���i�[���郊�X�g
     *            List of all stored packages
     * @return �p�b�P�[�W�ꗗ���i�[�������X�g
     *         List of all stored packages
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
     * �w��p�b�P�[�W�z���̃N���X�����擾����B
     * Get class information in selected package.
     * @param iPackage
     *            �w��p�b�P�[�W
     *            Selected package
     * @return �N���X���(List�Ɋi�[���ꂽString��List)
     *         Class information (String List stored in the list)
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
     * �w��p�b�P�[�W�z���̃N���X���擾����B
     * Get classes in selected package.
     * @param iPackage
     *            �w��p�b�P�[�W
     *            Selected package
     * @return �N���X�ꗗ���i�[�������X�g
     *         List of all stored classes
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
     * �w��N���X�̏����擾����B
     * Get information of selected class.
     * @param iClass
     *            �w��N���X
     *            Selected class
     * @return �N���X���(List�Ɋi�[���ꂽString��List)
     *         Class information (Strings list stored in the list)
     */
    private List getClassInfo(IClass iClass) {
        List lines = new ArrayList();
        lines.add(getClassNameLine(iClass));
        lines.addAll(getAttributeLines(iClass));
        lines.addAll(getOperationLines(iClass));
        return lines;
    }

    /**
     * �N���X���̍s�̏����擾����B
     * Get class name line.
     * @param iClass
     *            �N���X
     *            Class
     * @return �N���X���̍s�̏��iString��List�j
     *         Information of Class name lines (String list)
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
     * ��������C���^�[�t�F�[�X�����擾����B
     * Get Realization interface names.
     * @param iClass
     *            �N���X
     *            Class
     * @return �C���^�[�t�F�[�X��
     *         Interface Name
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
     * �p������N���X�����擾����B
     * Get generalization class names.
     * @param iClass
     *            �N���X
     *            Class
     * @return �N���X��
     *         Class name
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
     * �N���X�̖��O���t���p�X�Ŏ擾����B
     * Get Class name as Full Path.
     * @param iClass
     *            �N���X
     *            Class
     * @return �N���X���i�t���p�X�j
     *         Class Name (Full Path)
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
     * �S�����̏����擾����B
     * Get all Attribute information.
     * @param iClass
     *            �N���X
     *            Class
     * @return �S�����̏��iList�Ɋi�[���ꂽString��List�j
     *         All information of all attributes (String List stored in the list)
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
     * �����̏����擾����B
     * Get Attribute information.
     * @param iAttribute
     *            ����
     *            Attribute
     * @return �����̏��iString��List�j
     *         Attribute information (String List)
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
     * �����̃V�O�l�`�����擾����B
     * Get attribute signature.
     * @param iAttribute
     *            ����
     *            Attribute
     * @return �����̃V�O�l�`��
     *         Attribute signature
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
     * �S����̏����擾����B
     * Get all operation information.
     * @param iClass
     *            �N���X
     *            Class
     * @return �S����̏��iList�Ɋi�[���ꂽString��List�j
     *         All operation information (String list stored in the list)
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
     * ����̏����擾����B
     * Get operation information.
     * @param iOperation
     *            ����
     *            Operation
     * @return ����̏��iString��List�j
     *         Operation information (String List)
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
     * ����̃V�O�l�`�����擾����B
     * Get operation signature.
     * @param iOperation
     *            ����
     *            Operation
     * @return ����̃V�O�l�`��
     *         Operation signature
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
     * �����𕶎���Ŏ擾����B
     * Get visibility as string.
     * @param iNamedElement
     *            ���O�t���v�f
     *            Named elements
     * @return ����
     *         Visibility
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
