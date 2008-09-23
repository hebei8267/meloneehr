/**
 * @author kaka
 */
Ext.onReady(function(){

    var eduStartDate = new Ext.form.DateField({
        name: 'eduStartDate',
        width: 155,
        altFormats: 'Y-m-d',
        format: 'Y-m-d'
    });
    eduStartDate.render('eduStartDate'); // div-di
    var eduEndDate = new Ext.form.DateField({
        name: 'eduEndDate',
        width: 155,
        altFormats: 'Y-m-d',
        format: 'Y-m-d'
    });
    eduEndDate.render('eduEndDate'); // div-di
    var educationTypeCombo = new Ext.form.ComboBox({
        triggerAction: 'all',
        width: 155,
        editable: false,
        transform: 'educationType' // 转变表单元素ID
    });
    
    var educateSpecialtyCombo = new Ext.form.ComboBox({
        triggerAction: 'all',
        width: 155,
        editable: false,
        transform: 'educateSpecialty' // 转变表单元素ID
    });
    
    
    var myData = [['A001', '北京大学', '大学', '计算机科学与技术', '2004-9-1', '2008-7-1'], 
	['A002', '北京朝阳中学', '中学', '', '2001-9-1', '2004-7-1'],
	['A003', '北京朝阳小学', '小学', '', '1995-9-1', '2001-7-1']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'eduID'
        }, {
            name: 'schoolName'
        }, {
            name: 'educationType'
        }, {
            name: 'educateSpecialty'
        }, {
            name: 'eduStartDate'
        }, {
            name: 'eduEndDate'
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
        id: 'eduInfoGrid',
        el: 'eduInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'eduID',
            header: "编号",
            width: 80,
            sortable: true,
            dataIndex: 'eduID'
        }, {
            id: 'schoolName',
            header: "学校名称",
            width: 100,
            sortable: true,
            dataIndex: 'schoolName'
        }, {
            id: 'educationType',
            header: "教育类型",
            width: 90,
            sortable: true,
            dataIndex: 'educationType'
        }, {
            id: 'educateSpecialty',
            header: "教育专业",
            width: 110,
            sortable: true,
            dataIndex: 'educateSpecialty'
        }, {
            id: 'eduStartDate',
            header: "入学时间",
            width: 100,
            sortable: true,
            dataIndex: 'eduStartDate'
        }, {
            id: 'eduEndDate',
            header: "毕业时间",
            width: 100,
            sortable: true,
            dataIndex: 'eduEndDate'
        }],
        stripeRows: true,
        height: 200,
        width: 645,
        title: '教育经历信息'
    });
    
    grid.render();
});
