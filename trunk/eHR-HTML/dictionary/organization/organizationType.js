/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['00000001', '总部', ''], 
	['00000002', '分公司', ''], 
	['00000003', '子公司', ''], 
	['00000004', '部门', '']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'countryID'
        }, {
            name: 'countryName'
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
        id: 'organizationTypeInfoGrid',
        el: 'organizationTypeInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'countryID',
            header: "组织类型编号",
            width: 80,
            sortable: true,
            dataIndex: 'countryID'
        }, {
            id: 'countryName',
            header: "组织类型名称",
            width: 150,
            sortable: true,
            dataIndex: 'countryName'
        }],
        stripeRows: true,
        height: 300,
        width: 300,
        title: '组织类型信息列表'
    });
    
    grid.render();
});
