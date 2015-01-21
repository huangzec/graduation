
/**
 * @version $Id$
 * @author xjiujiu <xjiujiu@foxmail.com>
 * @description HongJuZi Framework
 * @copyright Copyright (c) 2011-2012 http://www.xjiujiu.com.All right reserved
 */

 HHJsLib.extend({

    /**
     * 成功信息提醒弹框
     * 
     * @author xjiujiu <xjiujiu@foxmail.com>
     * @access public
     * @param  String msg 提示信息
     * @return HHJsLib 库对象
     */
    succeed: function(msg, callback) {
        this.showNotification(msg, 'succeed', callback);

        return false;
     },

     /**
      * 提醒级别的信息提示
      * 
      * 如输入验证有错
      * 
      * @author xjiujiu <xjiujiu@foxmail.com>
      * @access public
      */
     notice: function(msg, callback) {
        this.showNotification(msg, 'warning', callback);

        return false;
     },

    /**
     * 信息提醒弹框
     * 
     * @author xjiujiu <xjiujiu@foxmail.com>
     * @access public
     * @param  String msg 提示信息
     * @return HHJsLib 库对象
     */
    info: function(msg, callback) {
        this.showNotification(msg, 'information', callback);

        return false;
     },

     /**
      * 第三方公用的显示方法
      * 
      * @author xjiujiu <xjiujiu@foxmail.com>
      * @access public
      * @param  String msg 需要显示的信息
      * @param  String type 信息类型 
      */
     showNotification: function(msg, type, callback) {
        var message     = this.getMessage(msg);
        this.focusTarget(msg.dom);
        HHJsLib.importCss([cdnUrl + "/jquery/plugins/notification/css/jquery.notification.css"]);
        HHJsLib.importJs([cdnUrl + "/jquery/plugins/notification/jquery.notification.js"], function() {
            var closeTime   = 3;
            showNotification({
                message: message + "（此信息<span id='close-time'>" + closeTime + "</span>秒后将自动关闭。）",
                type: type,
                autoClose: true,
                duration: closeTime
            });
            var timer   = setInterval(function() {
                var time    = parseInt($("#close-time").text()) - 1;
                if(time < 1) {
                    time    = 0;
                    clearInterval(timer);
                }
                $("#close-time").html(time);
            }, closeTime * 300);
            if('undefined' !== typeof(callback)) {
                setTimeout(function() {
                    callback();
                }, 1000);
            }
        });
     },

     /**
      * @var dialogTpl: 弹框使用的模板
      */
     dialogTpl: '<div id="dialog-{id}" data-id="{id}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
     + ' <div class="modal-header">'
     + ' <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>'
     + ' <h3 id="myModalLabel">{title}</h3>'
     + ' </div>'
     + ' <div class="modal-body"><div class="alert alert-{type}"><strong>{content}</strong></div></div>'
     + ' <div class="modal-footer">'
     + ' <button class="btn btn-primary" data-id="{id}" id="ok-btn-{id}">确认</button>'
     + ' </div>'
     + ' </div>',

     /**
      * 重新定义当前弹框的显示方法
      * 
      * 使用Bootstrap的内置方法
      * 
      * @author xjiujiu <xjiujiu@foxmail.com>
      * @access public
      * @param  Object msg 需要显示的对象
      * @param  String title 标题
      * @param  Function callback 回调函数
      * @return false
      */
     dialog: function(msg, type, callback) {
        this.focusTarget(msg.dom);
        title           = '系统助手提示您';
        var id          = new Date().valueOf();
        var message     = this.dialogTpl.replace(/{title}/gi, title);
        message         = message.replace(/{id}/gi, id);
        message         = message.replace(/{type}/gi, type);
        message         = message.replace(/{content}/gi, this.getMessage(msg));
        this.dialogByHtml(message, '#dialog-' + id);
        var $dialog     = $('#dialog-' + id);
        $("#ok-btn-" + id).click(function() {
            $dialog.modal('hide');
            'undefined' === typeof(callback) ? false : callback($dialog);
        });

        return false;
     },

     /**
      * 自定义HTML内容的弹框方法
      * 
      * 由于需要绑定按钮事件，所以需要把按钮也放到HTML中
      * 
      * @author xjiujiu <xjiujiu@foxmail.com>
      * @access public
      * @param  String content 需要显示的内容
      * @param  String target 需要显示的弹框对象
      * @return  false
      */
     dialogByHtml: function(content, target) {
        $("body").append(content);
        var $dialog     = $(target);
        $dialog.modal('show').on('hide, hidden', function() {
            $dialog.remove();
        });

        return false;
     }
 });
