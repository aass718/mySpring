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
                    if(callback){
                      // callback(data);
                    callback(data.replyCnt, data.list)
                    
                    }
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

    function displayTime(timeValue){
      //오늘 날짜를 가지고 옴.
      const today = new Date();
      const gap = today.getTime() - timeValue;
      const dateObj = new Date(timeValue);
      let str = "";
      if(gap < (1000 * 60 * 60 * 24)){
        //시간
        // const hh = dateObj.getHours();
        // const hh = dateObj.getHours().toString().padStart(2, "0"); 
        //이렇게 할 경우 return을 할 때 (hh > 9 ? "":"0")  조건문을 사용하지 않아도 됨.
        //분
        // const mi = dateObj.getMinutes();
        //초
        // const ss =dateObj.getSeconds();

        // return [(hh > 9 ? "":"0") + hh , ":", 
        //         (mi > 9 ? "" : "0") + mi, ":",
        //         (ss > 9 ? "" : "0") + ss].join('');

        const hh = dateObj.getHours().toString().padStart(2, "0"); 
        const mi = dateObj.getMinutes().toString().padStart(2, "0"); 
        const ss = dateObj.getSeconds().toString().padStart(2, "0"); 
        return hh+ ":"+mi +":"+ss;

      }else{
        const yy = dateObj.getFullYear();
        const mm = dateObj.getMonth();
        const dd = dateObj.getDate();

        return [yy, "/", 
                (mm > 9 ? "" : "0") + mm,"/",
                (dd > 9 ? "" : "0") + dd].join('');
      }
    }




    //리턴 꼭 하기~
    return {
        add:add,
        getList:getList,
        remove:remove,
        update:update,
        get:get,
        displayTime:displayTime 
    };

 }
 )();
 //replyService 에 들어가는 함수 


 
 