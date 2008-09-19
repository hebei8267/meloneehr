/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['00000001', '正常', ''], 
	['00000002', '带薪休假', ''], 
	['00000003', '在家待产', ''], 
	['00000004', '病假', ''], 
	['00000005', '事假', ''], 
	['00000006', '换休', ''], 
	['00000007', '年休', '']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'employeeWorkStateID'
        }, {
            name: 'employeeWorkStateName'
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
        id: 'employeeWorkStateInfoGrid',
        el: 'employeeWorkStateInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'employeeWorkStateID',
            header: "编号",
            width: 80,
            sortable: true,
            dataIndex: 'employeeWorkStateID'
        }, {
            id: 'employeeWorkStateName',
            header: "名称",
            width: 150,
            sortable: true,
            dataIndex: 'employeeWorkStateName'
        }],
        stripeRows: true,
        height: 300,
        width: 300,
        title: '员工当前工作状态信息'
    });
    
    grid.render();
});
