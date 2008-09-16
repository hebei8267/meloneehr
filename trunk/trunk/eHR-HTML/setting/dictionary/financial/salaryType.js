/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['A001', '基本工资', ''],
	 ['A002', '岗位工资', ''], 
	 ['A003', '工龄工资', ''],
	 ['A004', '职岗津贴', ''],
	 ['A005', '住房补贴', ''],
	 ['A006', '全勤奖', ''],
	 ['A007', '目标奖', ''],
	 ['A008', '通讯费', '']];
    
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
        id: 'salaryTypeInfoGrid',
        el: 'salaryTypeInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'countryID',
            header: "薪酬福利类型编号",
            width: 110,
            sortable: true,
            dataIndex: 'countryID'
        }, {
            id: 'countryName',
            header: "薪酬福利类型名称",
            width: 130,
            sortable: true,
            dataIndex: 'countryName'
        }],
        stripeRows: true,
        height: 300,
        width: 300,
        title: '薪酬福利类型信息'
    });
    
    grid.render();
});
