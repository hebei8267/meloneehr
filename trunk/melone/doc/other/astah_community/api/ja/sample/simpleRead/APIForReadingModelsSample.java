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
 * APIでAstahモデルのパッケージ情報およびクラス情報を取得するサンプルコード. 
 * パッケージ名やクラス名などをコンソールに出力する。
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

            // プロジェクトを開く。
            // 一番目のtrueでモデルバージョンチェック無しを指定する。
            // 二番目のfalseでプロジェクトファイルをロックさせない。
            // 三番目のtrueでプロジェクトファイルがロックされている場合、読込み専用モードでプロジェクトを開く。
            prjAccessor.open(PROJECT_PATH, true, false, true);

            System.out.println("Printing the elements...");

            // 起点となるモデルを取得する。
            IModel project = prjAccessor.getProject();

            // プロジェクトに含まれる全てのパッケージとクラスを取得する。
            printPackageInfo(project);

            // プロジェクトを閉じる
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
        // パッケージ名を表示
        System.out.println("Package name: " + iPackage.getName());
        // パッケージの定義を表示
        System.out.println("Package definition: " + iPackage.getDefinition());

        // パッケージの要素（クラスやパッケージ）を表示
        INamedElement[] iNamedElements = iPackage.getOwnedElements();
        for (int i = 0; i < iNamedElements.length; i++) {
            if (iNamedElements[i] instanceof IPackage) {
                IPackage iChildPackage = (IPackage) iNamedElements[i];
                // パッケージを表示
                printPackageInfo(iChildPackage);
            } else if (iNamedElements[i] instanceof IClass) {
                IClass iClass = (IClass) iNamedElements[i];
                // クラスを表示
                printClassInfo(iClass);
            }
        }
    }

    private static void printClassInfo(IClass iClass) {
        // クラス名を表示
        System.out.println("Class name: " + iClass.getName());
        // クラスの定義を表示
        System.out.println("Class definition: " + iClass.getDefinition());

        // 全ての属性を表示
        IAttribute[] iAttributes = iClass.getAttributes();
        for (int i = 0; i < iAttributes.length; i++) {
            printAttributeInfo(iAttributes[i]);
        }

        // 全ての操作を表示
        IOperation[] iOperations = iClass.getOperations();
        for (int i = 0; i < iOperations.length; i++) {
            printOperationInfo(iOperations[i]);
        }

        // インナークラスの情報を表示
        IClass[] iClasses = iClass.getNestedClasses();
        for (int i = 0; i < iClasses.length; i++) {
            printClassInfo(iClasses[i]);
        }
    }

    private static void printOperationInfo(IOperation iOperation) {
        // 操作名を表示
        System.out.println("Operation name: " + iOperation.getName());
        // 操作の返り値のタイプを表示
        System.out.println("Operation returnType: " + iOperation.getReturnTypeExpression());
        // 操作の定義を表示
        System.out.println("Operation definition: " + iOperation.getDefinition());
    }

    private static void printAttributeInfo(IAttribute iAttribute) {
        // 属性名を表示
        System.out.println("Attribute name: " + iAttribute.getName());
        // 属性のタイプを表示
        System.out.println("Attribute type: " + iAttribute.getTypeExpression());
        // 属性の定義を表示
        System.out.println("Attribute definition: " + iAttribute.getDefinition());
    }
}
