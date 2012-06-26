import java.io.IOException;

import com.change_vision.jude.api.inf.exception.LicenseNotFoundException;
import com.change_vision.jude.api.inf.exception.NonCompatibleException;
import com.change_vision.jude.api.inf.exception.ProjectLockedException;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IOperation;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;

/**
 * API��Astah���f���̃p�b�P�[�W��񂨂�уN���X�����擾����T���v���R�[�h. 
 * �p�b�P�[�W����N���X���Ȃǂ��R���\�[���ɏo�͂���B
 */
public class APIForReadingModelsSample {
	
	//installer
	private static final String PROJECT_PATH = "./SampleModel.asta";
	//eclipse
	//private static final String PROJECT_PATH = "api_sample\\simpleRead\\SampleModel.asta";

    public static void main(String[] args) {
        try {
            System.out.println("Opening project...");

            ProjectAccessor prjAccessor = ProjectAccessorFactory.getProjectAccessor();

            // �v���W�F�N�g���J���B
            // ��Ԗڂ�true�Ń��f���o�[�W�����`�F�b�N�������w�肷��B
            // ��Ԗڂ�false�Ńv���W�F�N�g�t�@�C�������b�N�����Ȃ��B
            // �O�Ԗڂ�true�Ńv���W�F�N�g�t�@�C�������b�N����Ă���ꍇ�A�Ǎ��ݐ�p���[�h�Ńv���W�F�N�g���J���B
            prjAccessor.open(PROJECT_PATH, true, false, true);

            System.out.println("Printing the elements...");

            // �N�_�ƂȂ郂�f�����擾����B
            IModel project = prjAccessor.getProject();

            // �v���W�F�N�g�Ɋ܂܂��S�Ẵp�b�P�[�W�ƃN���X���擾����B
            printPackageInfo(project);

            // �v���W�F�N�g�����
            prjAccessor.close();

            System.out.println("Finished");

        } catch (LicenseNotFoundException e) {
            e.printStackTrace();
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        } catch (ProjectLockedException e) {
            e.printStackTrace();
        } catch (NonCompatibleException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void printPackageInfo(IPackage iPackage) {
        // �p�b�P�[�W����\��
        System.out.println("Package name: " + iPackage.getName());
        // �p�b�P�[�W�̒�`��\��
        System.out.println("Package definition: " + iPackage.getDefinition());

        // �p�b�P�[�W�̗v�f�i�N���X��p�b�P�[�W�j��\��
        INamedElement[] iNamedElements = iPackage.getOwnedElements();
        for (int i = 0; i < iNamedElements.length; i++) {
            if (iNamedElements[i] instanceof IPackage) {
                IPackage iChildPackage = (IPackage) iNamedElements[i];
                // �p�b�P�[�W��\��
                printPackageInfo(iChildPackage);
            } else if (iNamedElements[i] instanceof IClass) {
                IClass iClass = (IClass) iNamedElements[i];
                // �N���X��\��
                printClassInfo(iClass);
            }
        }
    }

    private static void printClassInfo(IClass iClass) {
        // �N���X����\��
        System.out.println("Class name: " + iClass.getName());
        // �N���X�̒�`��\��
        System.out.println("Class definition: " + iClass.getDefinition());

        // �S�Ă̑�����\��
        IAttribute[] iAttributes = iClass.getAttributes();
        for (int i = 0; i < iAttributes.length; i++) {
            printAttributeInfo(iAttributes[i]);
        }

        // �S�Ă̑����\��
        IOperation[] iOperations = iClass.getOperations();
        for (int i = 0; i < iOperations.length; i++) {
            printOperationInfo(iOperations[i]);
        }

        // �C���i�[�N���X�̏���\��
        IClass[] iClasses = iClass.getNestedClasses();
        for (int i = 0; i < iClasses.length; i++) {
            printClassInfo(iClasses[i]);
        }
    }

    private static void printOperationInfo(IOperation iOperation) {
        // ���얼��\��
        System.out.println("Operation name: " + iOperation.getName());
        // ����̕Ԃ�l�̃^�C�v��\��
        System.out.println("Operation returnType: " + iOperation.getReturnTypeExpression());
        // ����̒�`��\��
        System.out.println("Operation definition: " + iOperation.getDefinition());
    }

    private static void printAttributeInfo(IAttribute iAttribute) {
        // ��������\��
        System.out.println("Attribute name: " + iAttribute.getName());
        // �����̃^�C�v��\��
        System.out.println("Attribute type: " + iAttribute.getTypeExpression());
        // �����̒�`��\��
        System.out.println("Attribute definition: " + iAttribute.getDefinition());
    }
}
