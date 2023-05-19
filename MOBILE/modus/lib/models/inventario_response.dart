import 'package:modus/models/page_info.dart';

import 'inventario.dart';

class InventarioResponse {
  List<Inventario> list;
  PageInfo pageInfo;

  InventarioResponse({
    required this.list,
    required this.pageInfo,
  });


  factory InventarioResponse.fromJson(Map<String, dynamic> json) => InventarioResponse(
    list: List<Inventario>.from(json["list"].map((x) => Inventario.fromJson(x))),
    pageInfo: PageInfo.fromJson(json["pageInfo"]),
  );
}