/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['00000001', '小学', ''], 
	['00000002', '初中', ''],
	['00000003', '高中', ''],
	['00000004', '大学专科', ''],
	['00000005', '大学本科', ''],
	['00000006', '研究生', ''],
	['00000007', '博士', ''],
	['00000008', '博士后', ''],
	['00000009', '其他', '']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'educationTypeID'
        }, {
            name: 'educationTypeName'
        }, {
            name: 'description'
        }]
    });
    store.loadData(myData);
    
    var sm = new Ext.grid.CheckboxSelectionModel({
        header: '',
        singleSelect: true,
        listeners: {
            rowselect: function(sm, row, rec){
                //	setFromData(rec);
            },
            rowdeselect: function(sm, row, rec){
                //		cleanFromData();
            }
        }
    });
    
    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        id: 'educationTypeInfoGrid',
        el: 'educationTypeInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'educationTypeID',
            header: "编号",
            width: 80,
            sortable: true,
            dataIndex: 'educationTypeID'
        }, {
            id: 'educationTypeName',
            header: "名称",
            width: 150,
            sortable: true,
            dataIndex: 'educationTypeName'
        }],
        stripeRows: true,
        height: 300,
        width: 300,
        title: '学历类型信息'
    });
    
    grid.render();
});
