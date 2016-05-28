int fat( int n ) {

   if ( n == 0 ) {
   	return 1;
   }

   return n * fat( n - 1 ) ;

}

void main( ) {

   int n, acc ;

   n = 0 ;

   acc = 0;

   while ( n < 6 ) {

      acc = acc + fat ( n - 1);

      n = n + 1 ;

   }
      println( acc ) ;

}