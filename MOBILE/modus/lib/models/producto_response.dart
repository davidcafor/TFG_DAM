import 'page_info.dart';
import 'producto.dart';

class ProductoResponse {
  List<Producto> list;
  PageInfo pageInfo;

  ProductoResponse({
    required this.list,
    required this.pageInfo,
  });

  factory ProductoResponse.fromJson(Map<String, dynamic> json) => ProductoResponse(
    list: List<Producto>.from(json["list"].map((x) => Producto.fromJson(x))),
    pageInfo: PageInfo.fromJson(json["pageInfo"]),
  );
}