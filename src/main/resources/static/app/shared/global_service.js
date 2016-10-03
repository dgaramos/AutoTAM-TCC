/**
 * Created by Danilo on 10/1/2016.
 */

'use strict';

services.factory('Global', function(){
    return {

        fechaModal : function (modalName){
            $(modalName).modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
        }
    }
});