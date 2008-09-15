/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['00000001', '正式员工', ''], 
	['00000002', '契约员工', ''], 
	['00000003', '暂时实行生', '']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'employTypeID'
        }, {
            name: 'employTypeName'
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
        id: 'employTypeInfoGrid',
        el: 'employTypeInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'employTypeID',
            header: "雇佣类型编号",
            width: 80,
            sortable: true,
            dataIndex: 'employTypeID'
        }, {
            id: 'employTypeName',
            header: "雇佣类型名称",
            width: 150,
            sortable: true,
            dataIndex: 'employTypeName'
        }],
        stripeRows: true,
        height: 300,
        width: 300,
        title: '雇佣类型信息'
    });
    
    grid.render();
});
