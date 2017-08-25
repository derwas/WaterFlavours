 $ ->
  $.get "/restapi/getmetaphors", (metaphors) ->
    $.each metaphors, (index, metaphor) ->
      description= "<form action=\'deleteMetaphor\' method=\'get\'>"
      description = description + metaphor.description 
      description = description + "  [<a href=\""+metaphor.reference+"\" target=\"_blank\">ref</a>]"
      description = description + "  <img src=\'"+metaphor.image+"\' alt=\'"+metaphor.description+"\' class=\'img-polaroid\' width=\'50\'>"
      description = description + "  <button class=\'btn btn-mini btn-danger\' type=\'submit\' name=\'id\' value=\'"+metaphor.id+"\'>Delete</button>"
      description = description + "</form>"
      $("#metaphors").append $("<li>").html description
