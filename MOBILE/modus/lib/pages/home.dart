import 'package:flutter/material.dart';

import '../widgets/widgets.dart';



class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromRGBO(68, 195, 235, 1),
        elevation: 0,
        title: Text('Modus'),
      ),
      drawer: Menu(),
      body: Container(
        width: double.infinity,
        height: double.infinity,
        child: Stack(
          children: [
            // Sección blanca
            Positioned.fill(
              child: Container(
                color: Colors.white,
              ),
            ),
            // Sección azul
            Positioned(
              top: 0,
              bottom: 0,
              left: MediaQuery.of(context).size.width * (2/3),
              right: 0,
              child: Container(
                color: Color.fromRGBO(68, 195, 235, 1),
              ),
            ),
            // Logo en el centro
            Positioned(
              top: 30,
              left: 50,
              child: Image.asset(
                'assets/images/logo.png',
                width: 150,
                height: 150,
              ),
            ),
            // Imagen de persona en la esquina inferior derecha
            Positioned(
              bottom: -10,
              right: -180,
              child: Image.asset(
                'assets/images/person.png',
                width: 600,
                height: 600,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
