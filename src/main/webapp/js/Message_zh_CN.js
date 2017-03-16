$.extend($.validator.messages, {
    required: "该字段不允许为空!",
    remote: "该字段已存在,请重新输入!",
    email: "请输入正确格式的电子邮件!",
    url: "请输入合法的网址!",
    date: "请输入合法的日期!",
    dateISO: "请输入合法的日期(例如yyyy/mm/dd或yyyy-mm-dd)!",
    number: "请输入合法的数字(整数或小数)!",
    digits: "请输入整型数据!",
    creditcard: "请输入合法的信用卡号!",
    equalTo: "两次输入的内容不一致!",
    accept: "文件后缀不符合要求!",
    maxlength: $.validator.format("输入内容长度不能大于{0}!"),
    minlength: $.validator.format("输入内容长度不能小于{0}!"),
    rangelength: $.validator.format("输入的数据长度应该为{0}-{1}之间"),
    range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值!"),
    max: $.validator.format("可以输入的最大值为 {0} !"),
    min: $.validator.format("可以输入的最小值为 {0} !")
});