	.data
_var1:	.word 0
_var2:	.word 0
_float1:	.float 0
	.text
		.globl main
main:	sw $ra, 0($sp)
		subu $sp, $sp, 4	#push $ra
		
		lw $ra, 4($sp)
		addiu $sp, $sp, 4	#pop $ra

