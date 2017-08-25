 $ ->
  $.get "/restapi/getuses", (uses) ->
    $.each uses, (index, use) ->
      description= "<form action=\'Uses/deleteUse\' method=\'get\'>"
      description = description + use.description 
      description = description + "  [<a href=\""+use.reference+"\" target=\"_blank\">ref</a>]"
      description = description + "  <img src=\'"+use.image+"\' alt=\'"+use.description+"\' class=\'img-polaroid\' width=\'50\'>"
      description = description + "  <button class=\'btn btn-mini btn-danger\' type=\'submit\' name=\'id\' value=\'"+use.id+"\'>Delete</button>"
      description = description + "</form>"
      $("#uses").append $("<li>").html description
