/**
 * @author kaka
 */
Ext.onReady(function(){

    var trainingStartDate = new Ext.form.DateField({
        name: 'trainingStartDate',
        width: 155,
        altFormats: 'Y-m-d',
        format: 'Y-m-d'
    });
    trainingStartDate.render('trainingStartDate'); // div-di
    
    var trainingEndDate = new Ext.form.DateField({
        name: 'trainingEndDate',
        width: 155,
        altFormats: 'Y-m-d',
        format: 'Y-m-d'
    });
    trainingEndDate.render('trainingEndDate'); // div-di
    
    
    
    var myData = [['A001', '如何做一个职场人', '金太阳培训学校', '北京天安门', '2008-7-1', '2008-7-3'], 
	['A002', '办公司卫生环境', '金太阳培训学校', '北京天安门', '2008-7-5', '2008-7-7'],
	['A003', '开发配置库管理', '金太阳培训学校', '北京天安门', '2008-7-9', '2008-7-11']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'trainingID'
        }, {
            name: 'trainingName'
        }, {
            name: 'trainingOrgName'
        }, {
            name: 'trainingSite'
        }, {
            name: 'startDate'
        }, {
            name: 'endDate'
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
        id: 'trainingInfoGrid',
        el: 'trainingInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'trainingID',
            header: "编号",
            width: 80,
            sortable: true,
            dataIndex: 'trainingID'
        }, {
            id: 'trainingName',
            header: "课程名称",
            width: 100,
            sortable: true,
            dataIndex: 'trainingName'
        }, {
            id: 'trainingOrgName',
            header: "培训机构名称",
            width: 90,
            sortable: true,
            dataIndex: 'trainingOrgName'
        }, {
            id: 'trainingSite',
            header: "培训地点",
            width: 110,
            sortable: true,
            dataIndex: 'trainingSite'
        }, {
            id: 'startDate',
            header: "开始时间",
            width: 100,
            sortable: true,
            dataIndex: 'startDate'
        }, {
            id: 'endDate',
            header: "结束时间",
            width: 100,
            sortable: true,
            dataIndex: 'endDate'
        }],
        stripeRows: true,
        height: 200,
        width: 645,
        title: '培训经历信息'
    });
    
    grid.render();
});
