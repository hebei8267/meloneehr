/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['00000001', '系统管理员'], ['00000002', '一般用户'], ['00000003', '行政人员']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'roleID'
        }, {
            name: 'roleName'
        }]
    });
    store.loadData(myData);
    
    var sm = new Ext.grid.CheckboxSelectionModel({
        header: '',
        singleSelect: true,
        listeners: {
            rowselect: function(sm, row, rec){
                // setFromData(rec);
            },
            rowdeselect: function(sm, row, rec){
                // cleanFromData();
            }
        }
    });
    
    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        id: 'roleGrid',
        el: 'roleGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'roleID',
            header: "编号",
            width: 80,
            sortable: true,
            dataIndex: 'roleID'
        }, {
            id: 'roleName',
            header: "名称",
            width: 150,
            sortable: true,
            dataIndex: 'roleName'
        }],
        stripeRows: true,
        height: 300,
        width: 307,
        title: '适用角色信息'
    });
    
    grid.render();
    
    //************************************************
    //************************************************
    var tree = new Ext.tree.TreePanel({
        el: 'menuTreeDiv',
        title: '菜单树信息',
        useArrows: true,
        animate: true,
        enableDD: false,
        containerScroll: true,
        bodyBorder: false,
        autoScroll: true,
        rootVisible: true,
        height: 300,
        width: 307,
        loader: new Ext.tree.TreeLoader()
    });
    
    var root = new Ext.tree.AsyncTreeNode({
        draggable: false,
        id: 'root',
        text: '菜单树根节点',
        icon: '../../images/root.gif',
        children: [{
            leaf: false,
            text: '系统设置',
            icon: '../../images/area.gif',
            children: [{
                leaf: true,
                text: '菜单树管理'
            }, {
                leaf: false,
                text: '湖北省',
                children: [{
                    leaf: true,
                    text: '武汉市'
                }, {
                    leaf: true,
                    text: '宜昌市'
                }]
            }]
        }, {
            leaf: false,
            icon: '../../images/area.gif',
            text: '人事管理'
        }, {
            leaf: false,
            icon: '../../images/area.gif',
            text: '财务管理'
        }]
    });
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
});
