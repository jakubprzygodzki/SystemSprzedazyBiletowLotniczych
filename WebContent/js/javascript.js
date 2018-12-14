    function sendAjax() {

        // get inputs
        var article = new Object();
        article.title = $('#title').val();
        article.url = $('#url').val();
        article.categories = $('#categories').val().split(";");
        article.tags = $('#tags').val().split(";");

  //       console.log(article);
  //
  //       var xhr = new XMLHttpRequest(),
	//  data = new FormData();
  //
  //       console.log(data);
  //
	// xhr.open("GET", "http://localhost:1080/BiletyLotnicze/jsonservlet", true);
  //
  //
	// xhr.onreadystatechange = function(e) {
  //
	// 	if(this.readyState === 4 && this.status === 200) {
  //
	// 		console.log(this);
	// 	}
  //
	// }
  //
	// data.append("title", article.title);
  // 	//data.append("tags", article.tags);
  //   	data.append("url", article.url );
  //     	//data.append("categories", article.categories);
  //     	console.log(data.get("title"));
  //
	// //xhr.setRequestHeader("Content-Type", "application/json");
	// xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	// //xhr.setRequestHeader("Content-Disposition", 19201);
	// //xhr.responseType = "blob";
	// xhr.send("title=dsfsdf&url=sdfsfsf&categories=sdfs&tags=5656d");
         $.ajax({
             url: "/jsonservlet",
            type: 'GET',
            dataType: 'json',
            data: JSON.stringify(article),
            contentType: 'application/json',
            mimeType: 'application/json',

            success: function (data) {
                $("tr:has(td)").remove();

                $.each(data, function (index, article) {

                    var td_categories = $("<td/>");
                    $.each(article.categories, function (i, tag) {
                        var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
                        span.text(tag);
                        td_categories.append(span);
                    });

                    var td_tags = $("<td/>");
                    $.each(article.tags, function (i, tag) {
                        var span = $("<span class='label' style='margin:4px;padding:4px' />");
                        span.text(tag);
                        td_tags.append(span);
                    });

                    $("#added-articles").append($('<tr/>')
                            .append($('<td/>').html("<a href='"+article.url+"'>"+article.title+"</a>"))
                            .append(td_categories)
                            .append(td_tags)
                    );

                });
            },
            error:function(data,status,er) {
                alert("error: "+data+" status: "+status+" er:"+er);
            }
        });
    }