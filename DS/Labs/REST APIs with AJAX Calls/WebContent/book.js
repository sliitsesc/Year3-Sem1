/**
 * 
 */

function getDetails(){
	jQuery.ajax({
        url: "http://localhost:8081/book_service/rest/books/" + $("#id").val(),
        type: "GET",
        contentType: "application/json",  
        dataType:'json',
        success: function(data, textStatus, errorThrown) {
            //here is your json.
              // process it
        	  $("#title").text(data.title);
        	  $("#price").text(data.price);

        },
        error : function(jqXHR, textStatus, errorThrown) {
        		$("#title").text("Sorry! Book not found!");
        		$("#price").text("");
        },
        timeout: 120000,
    });
};

function addNewBook(){
	
	console.log('executed');
	
    var bookId = $("#InsertBookId").val();
    var bookName = $("#InsertBookName").val();
    var bookPrice = $("#InsertBookPrice").val();
	
    var sendInfo = {
    		id: bookId ,
            title: bookName,
            price: bookPrice
    };
    console.log(sendInfo);
    
	jQuery.ajax({
		   type: "POST",
		  url: "http://localhost:8081/book_service/rest/books",
		  contentType: "application/json",
		  dataType: 'json',
		  data: JSON.stringify(sendInfo),
		  success: function(data) {
			  alert("Success");
			  $("#message").text("Added");
		  },
		    error: function(data){
		       alert("fail");
		       $("#message").text("Fail");
		  }
          
    });
};

function deleteBook(){
	console.log('executed')
	$.ajax({
		url: "http://localhost:8081/book_service/rest/books/" + $("#DeleteBookId").val(),
	    type: "DELETE",
	    contentType: "application/json",
	    success: function() {
	    	 $("#Deletemessage").text("Deleted");
	    },
	    error: function() {
	    	 $("#Deletemessage").text("Fail");
	    }
	});
}