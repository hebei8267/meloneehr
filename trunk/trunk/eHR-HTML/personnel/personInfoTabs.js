/**
 * @author kaka
 */
Ext.onReady(function(){
    var tabs = new Ext.TabPanel({
        id: 'personInfoTabs',
        renderTo: 'personInfoTabsDiv',
        activeTab: 0,
        plain: true,
        width: 725,
        defaults: {
            autoScroll: true
        },
        items: [{
            title: '基本信息',
            contentEl: 'personBaseInfo'
        }, {
            title: '雇用信息'
            //  ,contentEl: 'personBaseInfo'
        }, {
            title: '薪金福利信息'
            //  ,contentEl: 'personBaseInfo'
        }, {
            title: '教育经历信息',
            contentEl: 'personEduInfo'
        }, {
            title: '培训经历信息'
            //  ,contentEl: 'personBaseInfo'
        }]
    });
});
