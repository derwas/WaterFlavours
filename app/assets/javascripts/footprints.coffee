 $ ->
  $.get "/restapi/getfootprints", (footprints) ->
    $.each footprints, (index, footprint) ->
      description= "<form action=\'deleteFootprint\' method=\'get\'>"
      description = description + footprint.description 
      description = description + "  [<a href=\""+footprint.reference+"\" target=\"_blank\">ref</a>]"
      description = description + "  <img src=\'"+footprint.image+"\' alt=\'"+footprint.description+"\' class=\'img-polaroid\' width=\'50\'>"
      description = description + "  <button class=\'btn btn-mini btn-danger\' type=\'submit\' name=\'id\' value=\'"+footprint.id+"\'>Delete</button>"
      description = description + "</form>"
      $("#footprints").append $("<li>").html description
