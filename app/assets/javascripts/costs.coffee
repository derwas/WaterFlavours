 $ ->
  $.get "/restapi/getcosts", (costs) ->
    $.each costs, (index, cost) ->
      description= "<form action=\'/Costs/deleteCost\' method=\'get\'>"
      description = description + cost.description  
      description = description + "  [<a href=\""+cost.reference+"\" target=\"_blank\">ref</a>]"
      description = description + "  <button class=\'btn btn-mini btn-danger\' type=\'submit\' name=\'id\' value=\'"+cost.id+"\'>Delete</button>"
      description = description + "</form>"  
      $("#ulcosts").append $("<li>").html description