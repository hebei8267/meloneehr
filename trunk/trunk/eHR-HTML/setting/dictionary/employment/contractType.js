/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['00000001', '劳动合同', ''], 
	['00000002', '工作保密合同', ''], 
	['00000003', '培训合同', '']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'contractTypeID'
        }, {
            name: 'contractTypeName'
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
        id: 'contractTypeInfoGrid',
        el: 'contractTypeInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'contractTypeID',
            header: "编号",
            width: 80,
            sortable: true,
            dataIndex: 'contractTypeID'
        }, {
            id: 'contractTypeName',
            header: "名称",
            width: 150,
            sortable: true,
            dataIndex: 'contractTypeName'
        }],
        stripeRows: true,
        height: 300,
        width: 300,
        title: '合同类型信息'
    });
    
    grid.render();
});
