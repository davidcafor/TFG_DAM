import 'models.dart';

class TiendaResponse {
  List<Tienda> list;
  PageInfo pageInfo;

  TiendaResponse({
    required this.list,
    required this.pageInfo,
  });

  factory TiendaResponse.fromJson(Map<String, dynamic> json) => TiendaResponse(
    list: List<Tienda>.from(
        json["list"].map((x) => Tienda.fromJson(x))),
    pageInfo: PageInfo.fromJson(json["pageInfo"]),
  );
}