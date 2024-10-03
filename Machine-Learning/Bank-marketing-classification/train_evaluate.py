import pandas as pd
from sklearn.model_selection import cross_val_score
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, accuracy_score, roc_auc_score, roc_curve
import matplotlib.pyplot as plt

# Load the preprocessed data
desktop_path = r"C:\Users\georg\Desktop"
X_train_scaled = pd.read_csv(desktop_path + r'\X_train_scaled.csv')
X_test_scaled = pd.read_csv(desktop_path + r'\X_test_scaled.csv')
y_train = pd.read_csv(desktop_path + r'\y_train.csv').values.ravel()  
y_test = pd.read_csv(desktop_path + r'\y_test.csv').values.ravel()  

# Define the models
models = {
    'RandomForest': RandomForestClassifier(random_state=42),
    'LogisticRegression': LogisticRegression(max_iter=1000, random_state=42),
    'GradientBoosting': GradientBoostingClassifier(random_state=42)
}

# Function to train and evaluate models with cross-validation
def train_and_evaluate(models, X_train, y_train, X_test, y_test):
    results = {}
    plt.figure(figsize=(12, 8))
    
    for name, model in models.items():
        print(f"Training {name}...")
        
        # 10-fold Cross-validation
        cv_scores = cross_val_score(model, X_train, y_train, cv=10, scoring='accuracy')
        
        # Train on the full training data
        model.fit(X_train, y_train)
        
        # Predictions on the test set
        test_predictions = model.predict(X_test)
        
        # Test Set Accuracy
        test_accuracy = accuracy_score(y_test, test_predictions)
        
        # ROC AUC
        y_test_proba = model.predict_proba(X_test)[:, 1]
        roc_auc = roc_auc_score(y_test, y_test_proba)
        fpr, tpr, _ = roc_curve(y_test, y_test_proba)
        
        print(f"\n{name} Results")
        print(f"Cross-Validation Accuracy: {cv_scores.mean():.4f}")
        print(f"Test Set Accuracy: {test_accuracy:.4f}")
        print(f"ROC AUC: {roc_auc:.4f}")
        
        print(f"Classification Report for Test Set:\n{classification_report(y_test, test_predictions)}")
        
        results[name] = {
            'cv_accuracy': cv_scores.mean(),
            'test_accuracy': test_accuracy,
            'roc_auc': roc_auc,
            'classification_report': classification_report(y_test, test_predictions, output_dict=True)
        }
        
        # Plot ROC curve
        plt.plot(fpr, tpr, label=f'{name} (AUC = {roc_auc:.4f})')
    
    # Plot formatting
    plt.plot([0, 1], [0, 1], 'k--')
    plt.xlabel('False Positive Rate')
    plt.ylabel('True Positive Rate')
    plt.title('ROC Curve')
    plt.legend(loc='lower right')
    plt.grid(True)
    plt.savefig(desktop_path + r'\roc_curve.png')
    plt.show()
    
    return results

results = train_and_evaluate(models, X_train_scaled, y_train, X_test_scaled, y_test)

results_df = pd.DataFrame(results).T
results_df.to_csv(desktop_path + r'\model_results_with_cv.csv')

print("Training and evaluation complete. Results saved to 'model_results_with_cv.csv' on the desktop and ROC curve saved to 'roc_curve.png'.")
