import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:provider/provider.dart';
import '../models/inventario.dart';
import '../models/producto.dart';
import '../providers/inventario_provider.dart';
import '../providers/producto_provider.dart';
import 'package:intl/intl.dart';

class Detalle extends StatefulWidget {
  const Detalle({Key? key}) : super(key: key);

  @override
  State<Detalle> createState() => _DetalleState();
}

class _DetalleState extends State<Detalle> {

  String? ip;

  void getIpFromSharedPreferences() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      ip = prefs.getString('ip');
    });
  }

  @override
  void initState() {
    super.initState();
    getIpFromSharedPreferences();
  }

  @override
  Widget build(BuildContext context) {
    Producto producto = ModalRoute.of(context)!.settings.arguments as Producto;

    final providerInventario = Provider.of<InventarioProvider>(context, listen: false);
    final provider = Provider.of<ProductoProvider>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        title: Text('Detalles del producto'),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Expanded(
            flex: 2,
            child: Container(
              color: Colors.grey,
              child: ip != null && producto.imagen != null
                  ? FadeInImage.assetNetwork(
                placeholder: 'assets/images/placeholder.png',
                image: 'http://$ip${producto.imagen}',
                fit: BoxFit.cover,
              )
                  : Image.asset(
                'assets/images/default.png',
                fit: BoxFit.cover,
              ),
            ),
          ),
          Expanded(
            flex: 3,
            child: Container(
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(20.0),
                  topRight: Radius.circular(20.0),
                ),
              ),
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        Expanded(
                          flex: 3,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Container(
                                padding: EdgeInsets.all(8.0),
                                child: Text(
                                  producto.nombre,
                                  style: TextStyle(
                                    fontSize: 24,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                              ),
                              SizedBox(height: 8),
                              Container(
                                padding: EdgeInsets.all(8.0),
                                child: Text(
                                  producto.descripcion,
                                  style: TextStyle(
                                    fontSize: 16,
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ),
                        Expanded(
                          flex: 1,
                          child: Container(
                            color: Colors.black,
                            alignment: Alignment.center,
                            child: Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Text(
                                'Precio: ${producto.precio}€',
                                style: TextStyle(
                                  fontSize: 16,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.white,
                                ),
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                    SizedBox(height: 16),
                    Container(
                      height: 1,
                      color: Colors.grey,
                    ),
                    SizedBox(height: 16),
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 8.0),
                      child: Text(
                        'Stock en tienda',
                        style: TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                    SizedBox(height: 16),
                    Expanded(
                      child: FutureBuilder(
                        future: providerInventario.listaInventario(),
                        builder: (BuildContext context,
                            AsyncSnapshot<List<Inventario>> snapshot) {
                          if (snapshot.hasData) {
                            List<Inventario> inventarioList = snapshot.data!;
                            List<Tiendas> tiendasFiltradas = inventarioList
                                .where((inventario) =>
                            inventario.productos.id == producto.id)
                                .map((inventario) => inventario.tiendas)
                                .toList();

                            tiendasFiltradas.sort(
                                    (a, b) => a.nombre.compareTo(b.nombre));

                            return ListView.separated(
                              itemCount: tiendasFiltradas.length,
                              itemBuilder: (context, index) {
                                final tienda = tiendasFiltradas[index];
                                final inv = inventarioList[index];
                                // Formatea la fecha en el formato dd-mm-yyyy
                                final formattedDate =
                                DateFormat('dd-MM-yyyy').format(inv.ultimaActualizacion);

                                return ListTile(
                                  title: Text(tienda.nombre),
                                  subtitle: Text(
                                        'Últ. actualización: $formattedDate',
                                      ),


                                  trailing: Text('Stock: ${inv.cantidad}'),
                                );
                              },
                              separatorBuilder:
                                  (BuildContext context, int index) {
                                return Divider(
                                  thickness: 1,
                                );
                              },
                            );
                          } else {
                            return Center(
                              child: CircularProgressIndicator(),
                            );
                          }
                        },
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
