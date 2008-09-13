/**
 * @author kaka
 */
Ext.onReady(function(){

    var myData = [['N001', '汉族', '汉族是中国的主体民族'],
	 ['N002', '蒙古族', '蒙古族现主要分布在中国内蒙古区'],
	  ['N003', '回族', '回族是目前中国分布最广的少数民族'],
	  ['N004', '藏族', '藏族是主要居住在中国境内的操藏语的民族']];
    
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
        id: 'nationInfoGrid',
        el: 'nationInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'countryID',
            header: "民族编号",
            width: 80,
            sortable: true,
            dataIndex: 'countryID'
        }, {
            id: 'countryName',
            header: "民族名称",
            width: 150,
            sortable: true,
            dataIndex: 'countryName'
        }],
        stripeRows: true,
        height: 300,
        width: 300,
        title: '民族信息'
    });
    
    grid.render();
});
