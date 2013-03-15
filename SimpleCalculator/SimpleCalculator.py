import sys

OPERATORS = ["+", "-", "*", "/", "(", ")"]
def isOperator(s):
	return s in OPERATORS

def isLessPrecedence(operator_1, operator_2):
	if (operator_1 == "+" and operator_2 == "-") or (operator_1 == "-" and operator_2 == "+"):
		return False
	if (operator_1 == "*" and operator_2 == "/") or (operator_1 == "/" and operator_2 == "*"):
		return False
	if (operator_1 == "*" or operator_1 == "/") and (operator_2 == "+" or operator_2 == "-"):
		return False 
	return True

def isLeftParen(operator):
	return operator == "("

def isRightParen(operator):
	return operator == ")"

def getTokens(exp_string):
	tokens = list()
	s = ""
	for op in exp_string:
		if op != " ":
			if isOperator(op):
				if s != "": 
					tokens.append(s)
					s = ""
				tokens.append(op)
			else:
				s += op
	if s != "": tokens.append(s)
	tokens.append("end")
	return tokens

class TreeNode:
	def __init__(self, value, left=None, right=None):
		self.value = value
		self.left = left
		self.right = right

class SimpleCalculator:
	def __init__(self, exp_string):
		expressionTree = self.buildExpressionTree(exp_string)
		self.value = self.evalExpression(expressionTree)
 
	def buildExpressionTree(self, exp_string):
		operator_stack = list()
		operand_stack = list()
		tokens = getTokens(exp_string)
		for token in tokens:
			if (token == "end"):
				while (len(operator_stack) != 0):
					operator = operator_stack.pop()
					operand_2 = operand_stack.pop()
					operand_1 = operand_stack.pop()
					node = TreeNode(operator, operand_1, operand_2)
					operand_stack.append(node)

			elif isOperator(token):
				if len(operator_stack) == 0:
					operator_stack.append(token)
				else:
					if isLeftParen(token):
						operator_stack.append(token)
					elif isRightParen(token):
						operator = operator_stack.pop()
						operand_2 = operand_stack.pop()
						while (not isLeftParen(operator)):
							operand_1 = operand_stack.pop()
							node = TreeNode(operator, operand_1, operand_2)
							operand_2 = node
							operator = operator_stack.pop()
						operand_stack.append(operand_2)
						#print "OPERATOR STACK: " + str(operator_stack)
						#print "OPERAND STACK: " + str([op.value for op in operand_stack])
					elif isLessPrecedence(operator_stack[-1], token):
						operator_stack.append(token)
					elif not isLessPrecedence(operator_stack[-1], token):
						operator = operator_stack[-1]
						while (not isLeftParen(operator)):	
							operand_2 = operand_stack.pop()
							operand_1 = operand_stack.pop()
							node = TreeNode(operator, operand_1, operand_2)
							operand_stack.append(node)
							operator_stack.pop()
							if len(operator_stack) != 0:
								operator = operator_stack[-1]
							else:
								break
						#print "OPERATOR STACK: " + str(operator_stack)
						#print "OPERAND STACK: " + str([op.value for op in operand_stack])

						operator_stack.append(token)	
			else:
				node = TreeNode(token)
				operand_stack.append(node)
			#print "OPERAND STACK: " + str([op.value for op in operand_stack])
			#print "OPERATOR STACK: " + str(operator_stack)
		return operand_stack[0]

	def evalExpression(self, root):
		op = root.value
		if not isOperator(op): return int(op)
		else:
			if op == "+": 	return self.evalExpression(root.left) + self.evalExpression(root.right)
			elif op == "-": return self.evalExpression(root.left) - self.evalExpression(root.right)
			elif op == "*": return self.evalExpression(root.left) * self.evalExpression(root.right)
			elif op == "/": return self.evalExpression(root.left) / self.evalExpression(root.right)

	def getValue(self):
		return self.value

def main(exp_string):
	try:
		cal = SimpleCalculator(exp_string)
		print "RESULT: " + str(cal.getValue())
	except:
		print "ERROR: The expression is not well-formed"
	
if __name__ == '__main__':
	main(sys.argv[1])

