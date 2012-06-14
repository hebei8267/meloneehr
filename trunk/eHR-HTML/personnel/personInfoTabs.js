/**
 * @author kaka
 */
Ext.onReady(function(){
    var tabs = new Ext.TabPanel({
        id: 'personInfoTabs',
        renderTo: 'personInfoTabsDiv',
        activeTab: 3,
        plain: true,
        width: 725,
        defaults: {
            autoScroll: true
        },
        items: [{
            title: '基本信息',
            autoLoad: {
                url: 'personBaseInfo.html',
                scripts: true
            }
        }, {
            title: '雇用信息',
            autoLoad: {
                url: 'personEmployInfo.html',
                scripts: true
            }
        }, {
            title: '薪金福利信息'
        }, {
            title: '教育经历信息',
            autoLoad: {
                url: 'personEduInfo.html',
                scripts: true
            }
        }, {
            title: '培训经历信息',
            autoLoad: {
                url: 'personTrainingInfo.html',
                scripts: true
            }
        }]
    });
});
