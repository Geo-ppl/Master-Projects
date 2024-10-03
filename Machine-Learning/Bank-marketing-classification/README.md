# Bank Marketing Campaign Prediction

This project aims to predict whether a customer will subscribe to a term deposit based on data from a bank marketing campaign. The prediction is made using several machine learning models, including Logistic Regression, Random Forest, and Gradient Boosting. The dataset is based on direct marketing campaigns of a Portuguese banking institution, and the task is to predict the binary outcome of customer subscription (`yes`/`no`).

## Dataset

The dataset used is the "Bank Marketing" dataset, containing information about bank clients contacted during direct marketing campaigns (phone calls). It includes 41,188 records and 20 features, such as demographic details, past campaign outcomes, and contact information. The target variable is `y`, indicating whether the customer subscribed to a term deposit ('yes' or 'no').

## Preprocessing

- **Column Removal:** The `duration` column was dropped as it directly influences the target variable and is not available prior to the call outcome.
- **Duplicate Removal:** 1,784 duplicate rows were identified and removed to ensure dataset uniqueness.
- **Handling Missing Values:** Imputed 'unknown' values in categorical features and applied logical assumptions for specific cases (e.g., assigning job roles based on education level).
- **Feature Engineering:** The `age` column was binned into categories, and One-Hot Encoding was applied to categorical variables.
- **Handling Class Imbalance:** The Synthetic Minority Over-sampling Technique (SMOTE) was applied to balance the classes in the dataset.
- **Feature Scaling:** Standardized features were scaled after splitting the dataset into 70% training and 30% testing sets.

## Model Training

- **Models Used:** Logistic Regression, Random Forest, and Gradient Boosting were trained and evaluated using 10-fold cross-validation.

## Evaluation Metrics

- **Accuracy:** Evaluates the overall performance of the model.
- **ROC AUC Score:** Measures the model's ability to distinguish between classes.
- **Classification Report:** Provides precision, recall, F1-score, and support for each class.

## Technologies Used

- **Python:** Core programming language for the project.
- **Pandas:** For data manipulation and preprocessing.
- **Scikit-learn:** For model training, evaluation, and preprocessing techniques.
- **Imbalanced-learn:** For handling class imbalance using SMOTE.
- **Matplotlib:** For plotting ROC curves and visualizing performance.

## How to Run the Project

1. **Clone the Repository:**
    Open a terminal (or command prompt) and navigate to the directory where you want to store the project. Run the following command to clone the entire repository:

   ```bash
   git clone https://github.com/Geo-ppl/Master-Projects.git
   ```

2. **Navigate to the Project Directory:**
    After cloning the repository, navigate to the specific project folder (`Bank-marketing-classification`) within the repository:

   ```bash
   cd Master-Projects/Machine-Learning/Bank-marketing-classification
   ```
3. **Install Dependencies:**
    Ensure you have Python installed on your machine. In the terminal (within the project directory), run the following command to install the required libraries:

    ```bash
    pip install -r requirements.txt
    ```
4. **Preprocess the Data:**
    Run the preprocessing script to prepare the dataset for modeling. This will load the raw data, preprocess it, and save the cleaned data for model training:

    ```bash
    python preprocessing.py
    ```
5. **Train and Evaluate Models:**
    To train the models and evaluate their performance, run the following command:
    ```bash
    python train_and_evaluate.py
    ```

## Results

The results of the model evaluations (cross-validation accuracy, test set accuracy, ROC AUC scores, etc.) are saved in the `model_results_with_cv.csv file`. The ROC curve comparison is saved as `roc_curve.png`.


   