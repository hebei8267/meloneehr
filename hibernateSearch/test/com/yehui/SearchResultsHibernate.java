package com.yehui;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchResultsHibernate {
    private static SessionFactory sf = null;
    private static Session session = null;
    private static Transaction tx = null;

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        sf = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        assertNotNull(sf);
        System.out.println("11111111111111111");
    }

    @Before
    public void setUp() throws Exception {
        session = sf.openSession();
        tx = session.beginTransaction();
        tx.begin();
        System.out.println("2222222222222222222");
    }

    @After
    public void tearDown() throws Exception {
        tx.commit();
        session.close();
        System.out.println("33333333333333333");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (sf != null)
            sf.close();
        System.out.println("444444444444444444444");
    }

    //@Test
    public void testAddDept() throws Exception {
        for (int i = 0; i < 10; i++) {
            Department dept = new Department();
            dept.setDeptName("Market");
            dept.setDeptNo("6000");

            Employee emp = new Employee();
            emp.setDept(dept);
            
            if (i == 0) {
                emp.setEmpName("中华人民共和国成立");
            } else if (i == 1) {
                emp.setEmpName("中国");
            } else if (i == 2) {
                emp.setEmpName("日本国");
            } else if (i == 3) {
                emp.setEmpName("中日韩");
            } else if (i == 4) {
                emp.setEmpName("韩国");
            } else if (i == 5) {
                emp.setEmpName("中日贸易");
            } else if (i == 6) {
                emp.setEmpName("亚洲贸易");
            } else if (i == 7) {
                emp.setEmpName("中日战争");
            } else if (i == 8) {
                emp.setEmpName("中日友好");
            } else if (i == 9) {
                emp.setEmpName("日本外包");
            }
            emp.setEmpNo("NO" + i);
            emp.setEmpSalary(8000d);

            session.save(emp);
        }

    }

    //
    // //
    // // @SuppressWarnings("unchecked")
    // // @Test
    // // public void testFindAll() throws Exception {
    // // Query query = session.createQuery("from Department");
    // //
    // // List<Department> deptList = query.list();
    // // System.out.println("Department " + deptList.size());
    // // assertTrue(deptList.size() > 0);
    // // }
    //
    @SuppressWarnings("unchecked")
    @Test
    public void testIndex() throws Exception {
        FullTextSession fullTextSession = Search.createFullTextSession(session);
        assertNotNull(session);

        QueryParser parser = new QueryParser("name", new ChineseAnalyzer());
        org.apache.lucene.search.Query luceneQuery = parser.parse("中共成立");
        Query hibQuery = fullTextSession.createFullTextQuery(luceneQuery, Employee.class);

        List<Employee> list = hibQuery.list();
        System.out.println("Employee " + list.size());
//        for (Employee employee : list) {
//            System.out.println(employee.getEmpName());
//            System.out.println(employee.getDept().getDeptName());
//        }
        assertTrue(list.size() > 0);
    }

    // @Test
    // public void testAdd() throws Exception {
    // Foo foo = new Foo();
    //
    // foo.setId(1);
    //
    // foo.setName("第一个hibernate search");
    //
    // foo.setTitle("好好学习，天天向上");
    //
    // session.save(foo);
    // }

    // @Test
    // public void testFooIndex() throws Exception {
    // FullTextSession fullTextSession = Search.createFullTextSession(session);
    // assertNotNull(session);
    //
    // QueryParser parser = new QueryParser("title", new StopAnalyzer());
    // org.apache.lucene.search.Query luceneQuery = parser.parse("好好学习");
    // Query hibQuery = fullTextSession.createFullTextQuery(luceneQuery,
    // Foo.class);
    //
    // List list = hibQuery.list();
    //
    // assertTrue(list.size() > 0);
    // }
}
