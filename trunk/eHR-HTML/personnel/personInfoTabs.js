/**
 * @author kaka
 */
Ext.onReady(function(){
    var tabs = new Ext.TabPanel({
        id: 'personInfoTabs',
        renderTo: 'personInfoTabsDiv',
        activeTab: 0,
        width: 670,
        height: 450,
        plain: true,
        defaults: {
            autoScroll: true
        },
        items: [{
            title: '基本信息'//,
         //   contentEl: 'personBaseInfo'
        }]
    });
});
