	.data
var1:	.word
var2: 	.word
	.text
		.globl main
main:	sw $ra, 0($sp)
		subu $sp, $sp, 4	#push $ra
		# main code goes here
		lw $ra, 4($sp)
		addiu $sp, $sp, 4	#pop $ra