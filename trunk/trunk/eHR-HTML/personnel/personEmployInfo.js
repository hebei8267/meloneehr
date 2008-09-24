/**
 * @author kaka
 */
Ext.onReady(function(){   
    var myData = [['A001', '劳动合同', '劳动合同', '2008-7-1', '2008-7-3', '有'], 
	['A002', '保密合同', '工作保密合同', '2008-7-5', '2008-7-7', '无'],
	['A003', '培训合同', '培训合同', '2008-7-9', '2008-7-11', '无']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'contractID'
        }, {
            name: 'contractName'
        }, {
            name: 'contractType'
        }, {
            name: 'startDate'
        }, {
            name: 'endDate'
        }, {
            name: 'attachment'
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
        id: 'contractInfoGrid',
        el: 'contractInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'contractID',
            header: "编号",
            width: 80,
            sortable: true,
            dataIndex: 'contractID'
        }, {
            id: 'contractName',
            header: "合同名称",
            width: 100,
            sortable: true,
            dataIndex: 'contractName'
        }, {
            id: 'contractType',
            header: "合同类型",
            width: 90,
            sortable: true,
            dataIndex: 'contractType'
        }, {
            id: 'startDate',
            header: "生效时间",
            width: 110,
            sortable: true,
            dataIndex: 'startDate'
        }, {
            id: 'endDate',
            header: "失效时间",
            width: 100,
            sortable: true,
            dataIndex: 'endDate'
        }, {
            id: 'attachment',
            header: "合同附件",
            width: 100,
            sortable: true,
            dataIndex: 'attachment'
        }],
        stripeRows: true,
        height: 200,
        width: 300,
        title: '合同信息'
    });
    
    grid.render();
});
