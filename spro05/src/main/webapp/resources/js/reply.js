/**
 * 
 */
 
 console.log("reply module..............");
 
 const replyService = (function(){
// 호출할 때 replyService.add 로 호출이 가능하다.  


    // add함수
    function add(reply, callback, error){
      console.log("................add reply..........................");

      $.ajax({
        type: 'POST',
        url: '/reply/new',
        data: JSON.stringify(reply),
        contentType: "application/json; charset=UTF-8",
        success: function(result, status, xhr){
          if(callback){
            callback(result);
          }
        },
        error: function(xhr, status, er){
          if(error){error(er)};
        }
      })

    }

    //grt list 함수
    function getList(param, callback, error){
        const bno = param.bno;
        const page = param.page||1;

        $.getJSON("/reply/pages/"+bno+"/" +page + ".json",
                  function(data){
                    if(callback){callback(data);}
                  }
                ).fail(function(xhr, status, err){
                        if(error){
                          error(err);
                        }
                    });
      }

    function remove(rno, callback, error){
      $.ajax({
        type: 'delete',
        url: '/reply/'+ rno,
        success: function(deleteResult, status, xhr){
          if(callback){
            callback(deleteResult);
          }
        },
        error: function(xhr, status, er){
          if(error){
            error(er);
          }
        }
      });
    }  


    function update(reply, callback, error){
      console.log("..........update Rno : "+ reply.rno);
      $.ajax({
        type: 'PUT',
        url: '/reply/'+reply.rno,
        data: JSON.stringify(reply),
        contentType: 'application/json; charset=UTF-8',
        success: function(result, status, xhr){
          if(callback){
            callback(result);
          }
        },
        error: function(xhr, status, er){
          if(error){
            error(er);
          }
        }
      });
    }


    function get(rno, callback, error){
      console.log(".......get.......... ");
      $.get("/reply/"+rno +".json", 
      function(result){
        if(callback){
          callback(result);
        }
      }).fail(function(xhr,status,err){
        if(error){
          error(err);
        }
      })
    }



    // function get(rno, callback, error){
    //     console.log("get----------------");

    //     $.ajax({
    //         type : 'get',
    //         url : '/reply/' + rno,
    //         data: JSON.stringify(rno),
    //         headers : {contentType: "application/json; charset=utf-8"},
    //         success : function(getResult, status, xhr) {
    //             console.log("xhr : ", xhr);
    //             if(callback) {
    //                 callback(getResult);
    //             }
    //         },
    //         error : function(xhr, status, err) {
    //             if(error) error(err);
    //         }
    //     })
    // }

    //리턴 꼭 하기~
    return {
        add:add,
        getList:getList,
        remove:remove,
        update:update,
        get:get
    };

 }
 )();
 //replyService 에 들어가는 함수 


 
 