import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../models/models.dart';
import '../providers/providers.dart';
import '../widgets/widgets.dart';

class Busqueda extends StatefulWidget {
  const Busqueda({Key? key}) : super(key: key);

  @override
  State<Busqueda> createState() => _BusquedaState();
}

class _BusquedaState extends State<Busqueda> {
  final TextEditingController _searchController = TextEditingController();
  List<Producto> _searchProducts = [];
  bool _isLoading = false;

  @override
  Widget build(BuildContext context) {
    final provider = Provider.of<ProductoProvider>(context, listen: false);

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromRGBO(68, 195, 235, 1),
        elevation: 0,
        title: Text('Búsqueda de productos'),
      ),
      body: Column(
        children: [
          Padding(
            padding: EdgeInsets.all(16.0),
            child: TextField(
              controller: _searchController,
              onChanged: (value) async {
                setState(() {
                  _isLoading = true;
                });

                if (value.isEmpty) {
                  setState(() {
                    _searchProducts = [];
                  });
                } else {
                  _searchProducts = await provider.listaProductosQuery(value);
                }
                setState(() {
                  _isLoading = false;
                });
              },
              decoration: InputDecoration(
                hintText: 'Buscar productos',
                border: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.white, width: 1.0),
                  borderRadius: BorderRadius.all(Radius.circular(15.0)),
                ),
              ),
            ),
          ),
          _isLoading
              ? CircularProgressIndicator()
              : Expanded(
                  child: ListView.separated(
                    itemCount: _searchProducts.length,
                    separatorBuilder: (BuildContext context, int index) =>
                        Divider(),
                    itemBuilder: (BuildContext context, int index) {
                      final product = _searchProducts[index];
                      return Column(
                        children: [
                          ElementoBusqueda(producto: product),
                        ],
                      );
                    },
                  ),
                ),
        ],
      ),
    );
  }
}
